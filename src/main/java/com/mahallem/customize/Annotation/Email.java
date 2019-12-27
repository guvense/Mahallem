package com.mahallem.customize.Annotation;

import com.mahallem.customize.EmailValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    String message() default "{wrong.phone.format}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
