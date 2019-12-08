package com.mahallem.Exception;

public class UsernameExistException extends BaseException {
    public UsernameExistException() {
        super(ExceptionCode.USERNAME_EXIST);
    }
}
