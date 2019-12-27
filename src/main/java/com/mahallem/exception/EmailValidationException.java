package com.mahallem.exception;

public class EmailValidationException extends BaseValidationException {
    public EmailValidationException() { super(ExceptionCode.EMAIL_VALIDATION_FAILED);}
}
