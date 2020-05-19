package com.mahallem.permission;

import com.mahallem.constants.ProgressStatus;
import com.mahallem.entity.Permission;
import com.mahallem.mapper.service.PermissionMapper;
import com.mahallem.resource.PermissionResource;
import com.mahallem.service.TaskService;
import com.mahallem.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskAssignPermissionOperationTest {

    @Mock
    private UserService userService;

    @Mock
    private TaskService taskService;

    @Spy
    private Permission permission = PermissionResource.permissionTaskAssign;

    @Mock
    private PermissionMapper permissionMapper;

    @InjectMocks
    private TaskAssignPermissionOperation taskAssignPermissionOperation;


    @Test
    public void approve_ValidPermission_CorrectOrderAndParameters() {


        taskAssignPermissionOperation.approve();

        InOrder inOrder = inOrder(userService, taskService);

        inOrder.verify(userService).setApproveUserPermission(permission);
        inOrder.verify(taskService).updateTaskProgressStatus(permission.getTaskId().toString(), ProgressStatus.OPEN);
        inOrder.verify(taskService).setTaskOwner(permission.getToUserId(), permission.getTaskId());
        inOrder.verify(userService).getUser(permission.getToUserId().toString());

    }

    @Test
    public void reject_ValidPermission_CorrectOrderAndParameters() {

        taskAssignPermissionOperation.reject();

        InOrder inOrder = inOrder(userService, taskService);
        inOrder.verify(userService).setRejectUserPermission(permission);
        inOrder.verify(taskService).updateTaskProgressStatus(permission.getTaskId().toString(), ProgressStatus.CREATED);
        inOrder.verify(userService).getUser(permission.getToUserId().toString());
    }


    @Test
    public void save_ValidPermission_CorrectOrderAndParameters() {

        taskAssignPermissionOperation.save();

        InOrder inOrder = inOrder(taskService, permissionMapper);
        inOrder.verify(taskService).updateTaskProgressStatus(permission.getTaskId().toString(), ProgressStatus.PENDING);
        inOrder.verify(permissionMapper).permissionToPermissionResponse(permission);
    }
}
