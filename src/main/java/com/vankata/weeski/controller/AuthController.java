package com.vankata.weeski.controller;

import com.vankata.weeski.domain.user.exception.EmailExistsException;
import com.vankata.weeski.domain.user.exception.UserRegistrationValidationException;
import com.vankata.weeski.payload.*;
import com.vankata.weeski.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(
            @Valid @RequestBody LoginRequest loginRequest) {

        JwtAuthenticationResponse response = this.authenticationService.login(loginRequest);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(
            @Valid @ModelAttribute RegisterRequest registerRequest,
            BindingResult bindingResult,
            @RequestParam(name = "image", required = false) MultipartFile multipartFile) {

        if (bindingResult.hasErrors()) {
            throw new UserRegistrationValidationException(bindingResult);
        }

        // Creating user's account
        ApiResponse response = this.authenticationService.register(registerRequest, multipartFile);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.CREATED);

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
