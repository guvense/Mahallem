package com.mahallem.permission;

import com.mahallem.dto.Response.PermissionResponse;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.Permission;
import com.mahallem.mapper.service.PermissionMapper;
import com.mahallem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class HouseInclusionPermissionOperation extends PermissionOperation {

    private final UserService userService;

    private final Permission permission;

    private final PermissionMapper permissionMapper;

    @Transactional
    @Override
    public <T> T approve() {
        UserResponse user = userService.userInfo(permission.getFromUserId().toString());
        String houseId = user.getHouse().getId();
        userService.setApproveUserPermission(permission);
        userService.addHouseIdToUser(permission.getToUserId().toString(), new ObjectId(houseId));
        return (T) userService.getUser(permission.getToUserId().toString());
    }

    @Transactional
    @Override
    public <T> T reject() {
        userService.setRejectUserPermission(permission);
        return (T)userService.getUser(permission.getToUserId().toString());
    }

    @Override
    public PermissionResponse save() {
        return permissionMapper.permissionToPermissionResponse(permission);
    }

}
