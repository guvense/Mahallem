package com.mahallem.exception;

public class UsernameExistException extends BaseException {
    public UsernameExistException() {
        super(ExceptionCode.USERNAME_EXIST);
    }
}
