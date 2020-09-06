package com.mahallem.exception;

public class EmailExistException extends BaseException {
    public EmailExistException() {
        super(ExceptionCode.EMAIL_EXIST_EXCEPTION);
    }
}
