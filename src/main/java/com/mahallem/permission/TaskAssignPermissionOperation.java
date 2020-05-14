package com.mahallem.permission;

import com.mahallem.constants.ProgressStatus;
import com.mahallem.dto.Response.PermissionResponse;
import com.mahallem.entity.Permission;
import com.mahallem.mapper.service.PermissionMapper;
import com.mahallem.service.TaskService;
import com.mahallem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class TaskAssignPermissionOperation extends PermissionOperation {
    private final UserService userService;
    private final TaskService taskService;
    private final Permission permission;
    private final PermissionMapper permissionMapper;

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public <T> T approve() {
        userService.setApproveUserPermission(permission);
        taskService.updateTaskProgressStatus(permission.getTaskId().toString(), ProgressStatus.OPEN);
        taskService.setTaskOwner(permission.getToUserId(), permission.getTaskId());
        return (T) userService.getUser(permission.getToUserId().toString());
    }

    @Override
    public <T> T reject() {
        // This should be done : @Serco
        return null;
    }

    @Override
    public PermissionResponse save() {
        taskService.updateTaskProgressStatus(permission.getTaskId().toString(), ProgressStatus.PENDING);
        return permissionMapper.permissionToPermissionResponse(permission);
    }

}
