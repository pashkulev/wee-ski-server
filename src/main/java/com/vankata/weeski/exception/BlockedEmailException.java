package com.vankata.weeski.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "This email has been blocked! Please choose another one!")
public class BlockedEmailException extends RuntimeException {
}
