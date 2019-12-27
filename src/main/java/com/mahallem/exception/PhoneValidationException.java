package com.mahallem.exception;

public class PhoneValidationException extends BaseValidationException {
    public PhoneValidationException() { super(ExceptionCode.PHONE_VALIDATION_FAILED);}
}
