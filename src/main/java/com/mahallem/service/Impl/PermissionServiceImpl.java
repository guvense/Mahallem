package com.mahallem.service.Impl;

import com.mahallem.constants.PermissionType;
import com.mahallem.dto.Request.PermissionRequest;
import com.mahallem.dto.Response.PermissionResponse;
import com.mahallem.entity.Permission;
import com.mahallem.exception.PermissionRequestExistException;
import com.mahallem.mapper.service.PermissionMapper;
import com.mahallem.repository.PermissionRepository;
import com.mahallem.service.PermissionService;
import com.mahallem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    private final UserService userService;

    private final PermissionMapper permissionMapper;

    public PermissionResponse createUserToHouseRequest(String userId, PermissionRequest permissionRequest) {

        checkPermissionExist(userId, permissionRequest);
        Permission permissionMapped = permissionMapper.permissionRequestToPermission(permissionRequest, userId);
        Permission permission = permissionRepository.save(permissionMapped);
        return permissionMapper.permissionToPermissionResponse(permission);
    }

    private void checkPermissionExist(String fromUserId, PermissionRequest permissionRequest) {

        ObjectId toUserId = userService.getUserIdFromUsername(permissionRequest.getToUserName());
        Permission permission = permissionRepository.getPermission(new ObjectId(fromUserId), toUserId, permissionRequest.getPermissionType());
         Optional.ofNullable(permission)
                .ifPresent(s -> {
                    throw new PermissionRequestExistException();
                });



    }
}
