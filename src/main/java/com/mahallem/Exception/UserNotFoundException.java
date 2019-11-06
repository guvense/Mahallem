package com.mahallem.Exception;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super(ExceptionCode.USER_NOT_FOUND);
    }
}
