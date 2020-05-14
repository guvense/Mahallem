package com.mahallem.permission;

public abstract class PermissionOperation {

    public abstract <T> T approve();

    public  abstract <T> T reject();

}
