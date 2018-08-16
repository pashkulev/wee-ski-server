package com.vankata.weeski.domain.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Validation failed!")
public class UserRegistrationValidationException extends RuntimeException {

    private List<String> errors;

    public UserRegistrationValidationException(BindingResult bindingResult) {
        super();
        this.setErrors(bindingResult);
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(this.errors);
    }

    private void setErrors(BindingResult bindingResult) {
        this.errors = new ArrayList<>();
        this.errors.addAll(bindingResult.getFieldErrors()
                .stream()
                .map(e -> String.format("%s: %s", e.getField(), e.getDefaultMessage()))
                .collect(Collectors.toList()));
        this.errors.addAll(bindingResult.getGlobalErrors()
                .stream()
                .map(e -> String.format("%s: %s", e.getObjectName(), e.getDefaultMessage()))
                .collect(Collectors.toList()));
    }
}