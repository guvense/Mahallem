package com.mahallem.permission;

import com.mahallem.exception.PermissionOperationNotExistException;

public class NonOfPermission extends PermissionOperation {
    @Override
    public <T> T approve() {
        throw  new PermissionOperationNotExistException();
    }
}
