package com.mahallem.service.Impl;

import com.mahallem.constants.PermissionType;
import com.mahallem.constants.ProgressStatus;
import com.mahallem.dto.Request.PermissionAnswerRequest;
import com.mahallem.dto.Request.PermissionRequest;
import com.mahallem.dto.Response.PermissionResponse;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.Permission;
import com.mahallem.exception.PermissionRequestExistException;
import com.mahallem.mapper.service.PermissionAnswerMapper;
import com.mahallem.mapper.service.PermissionMapper;
import com.mahallem.permission.PermissionFactory;
import com.mahallem.permission.PermissionOperation;
import com.mahallem.repository.PermissionRepository;
import com.mahallem.service.PermissionService;
import com.mahallem.service.TaskService;
import com.mahallem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    private final UserService userService;

    private final TaskService taskService;

    private final PermissionMapper permissionMapper;

    private final PermissionFactory permissionFactory;

    public PermissionResponse createUserToHouseRequest(String userId, PermissionRequest permissionRequest) {

        checkPermissionExist(userId, permissionRequest);
        Permission permissionMapped = permissionMapper.permissionRequestToPermission(permissionRequest, userId);
        Permission permission = permissionRepository.save(permissionMapped);
        return permissionMapper.permissionToPermissionResponse(permission);
    }

    @Override
    public Page<PermissionResponse> getAllPendingPermissionRequest(String userId, Pageable pageable) {

        List<Permission> allPendingPermissions = permissionRepository.getAllPendingPermissions(new ObjectId(userId), pageable);
        List<PermissionResponse> permissionResponses = permissionMapper.permissionToPermissionResponse(allPendingPermissions);
        return new PageImpl<>(permissionResponses);
    }

    @Override
    public UserResponse approvePermissionRequest(String userId, PermissionAnswerRequest permissionAnswerRequest) {

        Permission permission = PermissionAnswerMapper.map.permissionAnswerRequestToPermission(permissionAnswerRequest, userId);
        PermissionOperation permissionOperation = permissionFactory.getPermission(permission);
        return permissionOperation.approve();
    }

    @Override
    public UserResponse rejectPermissionRequest(String userId, PermissionAnswerRequest permissionAnswerRequest) {
        Permission permission = PermissionAnswerMapper.map.permissionAnswerRequestToPermission(permissionAnswerRequest, userId);
        PermissionOperation permissionOperation = permissionFactory.getPermission(permission);
        return permissionOperation.reject();
    }

    @Override
    public PermissionResponse assignTaskToUser(String userId, PermissionRequest permissionRequest) {
        checkPermissionExist(userId, permissionRequest);
        Permission permissionMapped = permissionMapper.permissionRequestToPermission(permissionRequest, userId);
        Permission permission = permissionRepository.save(permissionMapped);
        taskService.updateTaskProgressStatus(permissionRequest.getTaskId(), ProgressStatus.PENDING);
        return permissionMapper.permissionToPermissionResponse(permission);
    }

    private void checkPermissionExist(String fromUserId, PermissionRequest permissionRequest) {
        ObjectId toUserId = userService.getUserIdFromUsername(permissionRequest.getToUserName());
        Permission permission;
        if (permissionRequest.getPermissionType().equals(PermissionType.ADD_HOME)) {
            permission = permissionRepository.getPermission(new ObjectId(fromUserId), toUserId, permissionRequest.getPermissionType());
        } else {
            permission = permissionRepository.getPermission(new ObjectId(fromUserId), toUserId, permissionRequest.getPermissionType(), new ObjectId(permissionRequest.getTaskId()));
        }
        Optional.ofNullable(permission)
                .ifPresent(s -> {
                    throw new PermissionRequestExistException();
                });
    }
}
