package com.vankata.weeski.domain.user.validation;

import com.vankata.weeski.payload.RegisterRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) { }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        RegisterRequest user = (RegisterRequest) obj;
        if (user.getPassword() == null && user.getConfirmPassword() == null) {
            return true;
        }

        if (user.getPassword() == null) {
            return false;
        }

        return user.getPassword().equals(user.getConfirmPassword());
    }
}