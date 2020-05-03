package com.mahallem.exception;

public class PermissionOperationNotExistException extends BaseException {
   public PermissionOperationNotExistException() {
        super(ExceptionCode.PERMISSION_OPERATION_NOT_EXIST);
    }
}
