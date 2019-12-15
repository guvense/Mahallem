package com.mahallem.Exception;

public class HouseNotFoundException extends BaseException {
    public HouseNotFoundException() {
        super(ExceptionCode.HOUSE_NOT_FOUND);
    }

}
