package com.mahallem.permission;

import com.mahallem.constants.ProgressStatus;
import com.mahallem.entity.Permission;
import com.mahallem.service.TaskService;
import com.mahallem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class TaskAssignPermissionOperation extends PermissionOperation {
    private final UserService userService;
    private final TaskService taskService;
    private final Permission permission;

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public <T> T approve() {
        userService.setApproveUserPermission(permission);
        taskService.updateTaskProgressStatus(permission.getTaskId().toString(), ProgressStatus.OPEN);
        taskService.setTaskOwner(permission.getToUserId(), permission.getTaskId());
        return (T) userService.getUser(permission.getToUserId().toString());
    }
}
