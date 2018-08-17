//package com.vankata.weeski.controller;
//
//import com.vankata.weeski.payload.ApiError;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import java.util.ArrayList;
//import java.util.List;
//
//@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//
//        List<String> errors = new ArrayList<>();
//        for (FieldError exception : ex.getBindingResult().getFieldErrors()) {
//            errors.add(exception.getField() + ": " + exception.getDefaultMessage());
//        }
//
//        for (ObjectError exception : ex.getBindingResult().getGlobalErrors()) {
//            errors.add(exception.getObjectName() + ": " + exception.getDefaultMessage());
//        }
//
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
//        return this.handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMissingServletRequestParameter(
//            MissingServletRequestParameterException ex,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//
//        String exception = ex.getParameterName() + " parameter is missing";
//
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), exception);
//        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
//    }
//
//    @ExceptionHandler({ ConstraintViolationException.class })
//    public ResponseEntity<Object> handleConstraintViolation(
//            ConstraintViolationException ex,
//            WebRequest request) {
//
//        List<String> errors = new ArrayList<>();
//        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
//            errors.add(violation.getRootBeanClass().getName() + " " +
//                    violation.getPropertyPath() + ": " + violation.getMessage());
//        }
//
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
//        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
//    }
//
//    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
//    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
//            MethodArgumentTypeMismatchException ex,
//            WebRequest request) {
//
//        String exception = ex.getName() + " should be of type " + ex.getRequiredType().getName();
//
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), exception);
//        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
//    }
//}
//
