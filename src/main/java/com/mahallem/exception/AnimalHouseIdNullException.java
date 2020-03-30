package com.mahallem.exception;

public class AnimalHouseIdNullException extends BaseException {
    public AnimalHouseIdNullException() {
        super(ExceptionCode.HOUSE_ID_NULL_ANIMAL);
    }
}
