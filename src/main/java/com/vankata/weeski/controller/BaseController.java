package com.vankata.weeski.controller;

import com.vankata.weeski.payload.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public abstract class BaseController {

    protected ResponseEntity<ApiError> createErrorResponse(
            Class<? extends RuntimeException> exceptionClass,
            List<String> errors) {
        ResponseStatus responseStatusAnnotation = exceptionClass.getAnnotation(ResponseStatus.class);
        String message = responseStatusAnnotation.reason();
        HttpStatus httpStatus = responseStatusAnnotation.value();

        ApiError apiError = new ApiError(httpStatus, message, errors);
        return new ResponseEntity<>(apiError, new HttpHeaders(), httpStatus);
    }
}
