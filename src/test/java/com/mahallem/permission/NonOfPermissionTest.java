package com.mahallem.permission;

import com.mahallem.exception.PermissionOperationNotExistException;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NonOfPermissionTest {

    @Spy
    private NonOfPermission nonOfPermission;

    @Test(expected = PermissionOperationNotExistException.class)
    public void approve_Invoke_ThrownOperationNotExistException() {
        nonOfPermission.approve();
    }

    @Test(expected = PermissionOperationNotExistException.class)
    public void reject_Invoke_ThrownOperationNotExistException() {
        nonOfPermission.reject();
    }

    @Test(expected = PermissionOperationNotExistException.class)
    public void save_Invoke_ThrownOperationNotExistException() {
        nonOfPermission.save();
    }
}
