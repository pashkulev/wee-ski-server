package com.vankata.weeski.controllers;

import com.vankata.weeski.domain.user.exception.EmailExistsException;
import com.vankata.weeski.domain.user.exception.UserRegistrationValidationException;
import com.vankata.weeski.domain.user.model.User;
import com.vankata.weeski.domain.user.model.UserRegisterModel;
import com.vankata.weeski.domain.user.service.UserService;
import com.vankata.weeski.error.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User register(@Valid @ModelAttribute UserRegisterModel user,
                         BindingResult bindingResult,
                         @RequestParam(name = "image", required = false) MultipartFile multipartFile)
            throws IOException, EmailExistsException, UserRegistrationValidationException {

        if (bindingResult.hasErrors()) {
            throw new UserRegistrationValidationException(bindingResult);
        }

        return this.userService.register(user, multipartFile);
    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<ApiError> emailExistsHandler() {
        return this.createErrorResponse(EmailExistsException.class, Collections.emptyList());
    }

    @ExceptionHandler(UserRegistrationValidationException.class)
    public ResponseEntity<ApiError> validationFailedHandler(UserRegistrationValidationException ex) {
         return this.createErrorResponse(UserRegistrationValidationException.class, ex.getErrors());
    }

    private ResponseEntity<ApiError> createErrorResponse(
            Class<? extends RuntimeException> exceptionClass,
            List<String> errors) {
        ResponseStatus responseStatusAnnotation = exceptionClass.getAnnotation(ResponseStatus.class);
        String message = responseStatusAnnotation.reason();
        HttpStatus httpStatus = responseStatusAnnotation.value();

        ApiError apiError = new ApiError(httpStatus, message, errors);
        return new ResponseEntity<>(apiError, new HttpHeaders(), httpStatus);
    }
}