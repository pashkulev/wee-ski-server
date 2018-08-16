package com.vankata.weeski.domain.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "E-mail already exists!")
public class EmailExistsException extends RuntimeException {
}
