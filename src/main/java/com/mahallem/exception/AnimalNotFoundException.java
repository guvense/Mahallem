package com.mahallem.exception;

public class AnimalNotFoundException extends BaseException {
    public AnimalNotFoundException() {
        super(ExceptionCode.ANIMAL_NOT_FOUND);
    }
}
