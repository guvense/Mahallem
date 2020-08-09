package com.mahallem.service.impl;

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

    private final PermissionFactory permissionFactory;

    private final PermissionMapper permissionMapper;

    @Override
    public Page<PermissionResponse> getAllPendingPermissionRequest(String userId, Pageable pageable) {

        List<Permission> allPendingPermissions = permissionRepository.getAllPendingPermissions(userId, pageable);
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
    public PermissionResponse createPermission(String userId, PermissionRequest permissionRequest) {
        Permission permissionMapped = permissionMapper.permissionRequestToPermission(permissionRequest, userId);
        checkPermissionExist(userId, permissionMapped);
        Permission permission = permissionRepository.save(permissionMapped);
        PermissionOperation permissionOperation = permissionFactory.getPermission(permission);
        return permissionOperation.save();
    }

    private void checkPermissionExist(String fromUserId, Permission permission) {
        UserResponse user = userService.getUser(permission.getToUserId().toString());
        Optional<Permission> permissionOp = permissionRepository.getPermission(new ObjectId(fromUserId), new ObjectId(user.getId()), permission);
        permissionOp.ifPresent(s -> {
            throw new PermissionRequestExistException();
        });
    }
}
