package com.vankata.weeski.payload;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> errors;
    private LocalDateTime timestamp;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String message, String error) {
        this(status, message, Collections.singletonList(error));
    }
}