package com.mahallem.exception;

public class ProfilePictureUpdateException extends BaseException {
    public ProfilePictureUpdateException() {
        super(ExceptionCode.PROFILE_PICTURE_UPDATE_FAILED);
    }
}
