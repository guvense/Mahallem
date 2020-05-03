package com.mahallem.exception;

public class PermissionProgressUpdateException extends BaseException {
   public PermissionProgressUpdateException() {
        super(ExceptionCode.PERMISSION_PROGRESS_UPDATE_FAILED);
    }
}
