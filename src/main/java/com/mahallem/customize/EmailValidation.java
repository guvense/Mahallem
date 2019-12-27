package com.mahallem.customize;

import com.mahallem.customize.Annotation.Email;
import com.mahallem.exception.EmailValidationException;

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
