package com.mahallem.Exception;

public class EmailValidationException extends BaseValidationException {
    public EmailValidationException() { super(ExceptionCode.EMAIL_VALIDATION_FAILED);}
}
