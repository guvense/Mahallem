package com.mahallem.Exception;

import javax.validation.ConstraintDeclarationException;

public class BaseValidationException extends ConstraintDeclarationException {
     BaseValidationException(String code) { super(code); }
}
