package com.mahallem.exception;

public class HousePropertyExistException extends BaseException {
    public HousePropertyExistException() {
        super(ExceptionCode.HOUSE_PROPERTY_EXIST);
    }
}
