package com.mahallem.Exception;

import javax.validation.ConstraintDeclarationException;

public class BaseValidationException extends ConstraintDeclarationException {
    public BaseValidationException(int code) { super(String.valueOf(code)); }
}
