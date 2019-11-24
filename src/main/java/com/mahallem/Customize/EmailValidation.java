package com.mahallem.Customize;

import com.mahallem.Customize.Annotation.Email;
import com.mahallem.Exception.EmailValidationException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidation implements ConstraintValidator<Email, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String emailRegex = "^(.+)@(.+)$";
        if (s == null || !s.matches(emailRegex)) {
            throw new EmailValidationException();
        } else {
            return true;
        }
    }
}
