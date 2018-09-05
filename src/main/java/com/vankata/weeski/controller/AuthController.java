package com.vankata.weeski.controller;

import com.vankata.weeski.service.BlockedEmailService;
import com.vankata.weeski.exception.BlockedEmailException;
import com.vankata.weeski.exception.EmailExistsException;
import com.vankata.weeski.exception.ValidationException;
import com.vankata.weeski.payload.*;
import com.vankata.weeski.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController {

    private final AuthenticationService authenticationService;

    private final BlockedEmailService blockedEmailService;

    @Autowired
    public AuthController(AuthenticationService authenticationService, BlockedEmailService blockedEmailService) {
        this.authenticationService = authenticationService;
        this.blockedEmailService = blockedEmailService;
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
            throw new ValidationException(bindingResult);
        }

        if (this.blockedEmailService.isEmailBlocked(registerRequest.getEmail())) {
            throw new BlockedEmailException();
        }

        ApiResponse response = this.authenticationService.register(registerRequest, multipartFile);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.CREATED);

    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<ApiError> emailExistsHandler() {
        return this.createErrorResponse(EmailExistsException.class, Collections.emptyList());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> validationFailedHandler(ValidationException ex) {
        return this.createErrorResponse(ValidationException.class, ex.getErrors());
    }

    @ExceptionHandler(BlockedEmailException.class)
    public ResponseEntity<ApiError> blockedEmailHandler() {
        return this.createErrorResponse(BlockedEmailException.class, Collections.emptyList());
    }
}