package com.mahallem.Exception;

public class AnimalNotFoundException extends BaseException {
    public AnimalNotFoundException() {
        super(ExceptionCode.ANIMAL_NOT_FOUND);
    }
}
