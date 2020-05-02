package com.mahallem.exception;

public class PermissionRequestExistException extends BaseException {
   public PermissionRequestExistException() {
        super(ExceptionCode.PERMISSION_REQUEST_EXIST);
    }
}
