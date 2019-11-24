package com.mahallem.Exception;

import javax.validation.ValidationException;

public class PhoneValidationException extends BaseValidationException {
    public PhoneValidationException() { super(ExceptionCode.PHONE_VALIDATION_FAILED);}
}
