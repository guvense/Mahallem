package com.mahallem.Customize.Annotation;

import com.mahallem.Customize.EmailValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    String message() default "Wrong Email Format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
