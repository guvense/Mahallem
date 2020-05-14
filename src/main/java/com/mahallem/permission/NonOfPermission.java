package com.mahallem.permission;

import com.mahallem.dto.Response.PermissionResponse;
import com.mahallem.exception.PermissionOperationNotExistException;

public class NonOfPermission extends PermissionOperation {
    @Override
    public <T> T approve() {
        throw new PermissionOperationNotExistException();
    }

    @Override
    public <T> T reject() {
        throw new PermissionOperationNotExistException();
    }

    @Override
    public PermissionResponse save() {
        throw new PermissionOperationNotExistException();
    }
}
