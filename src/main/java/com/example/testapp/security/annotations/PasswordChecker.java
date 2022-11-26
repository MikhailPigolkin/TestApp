package com.example.testapp.security.annotations;

import com.example.testapp.security.SignUpRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordChecker implements ConstraintValidator<PasswordValid, Object> {

    @Override
    public void initialize(PasswordValid constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        SignUpRequest userSignUpReq = (SignUpRequest) value;
        return userSignUpReq.getPassword().equals(userSignUpReq.getRepeatPassword());
    }
}
