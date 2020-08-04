package com.mahallem.exception;

public class UploadPictureFailedException extends BaseException {

    public UploadPictureFailedException() {
        super(ExceptionCode.UPLOAD_PICTURE_FAILED_EXCEPTION);
    }
}
