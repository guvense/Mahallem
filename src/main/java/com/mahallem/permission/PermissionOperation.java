package com.mahallem.permission;

import com.mahallem.dto.Response.PermissionResponse;

public abstract class PermissionOperation {

    public abstract <T> T approve();

    public  abstract <T> T reject();

    public abstract PermissionResponse save();

}
