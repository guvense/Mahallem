package com.mahallem.permission;

import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.Permission;
import com.mahallem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

@RequiredArgsConstructor
public class HouseInclusionPermissionOperation extends PermissionOperation {

    private final UserService userService;

    private final Permission permission;

    @Override
    @SuppressWarnings("unchecked")
    public <T> T approve() {
        UserResponse user = userService.userInfo(permission.getFromUserId().toString());
        String houseId = user.getHouse().getId();
        userService.setApproveUserPermission(permission);
        userService.addHouseIdToUser(permission.getToUserId().toString(), new ObjectId(houseId));
        return (T) userService.getUser(permission.getToUserId().toString());
    }


}