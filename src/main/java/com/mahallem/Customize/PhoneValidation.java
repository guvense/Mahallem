package com.mahallem.Customize;

import com.mahallem.Customize.Annotation.Phone;
import com.mahallem.Exception.PhoneValidationException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidation implements ConstraintValidator<Phone, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (s == null) {
            throw new PhoneValidationException();
        }

        if (s.matches("\\d{10}")) return true;

        else if (s.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;

        else if (s.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;

        else if (s.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;

        else throw new PhoneValidationException();

    }

}

