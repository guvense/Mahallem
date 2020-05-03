package com.mahallem.permission;

import com.mahallem.constants.PermissionType;
import com.mahallem.entity.Permission;
import com.mahallem.service.PermissionService;
import com.mahallem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissionFactory {

    private final UserService userService;
    public PermissionOperation getPermission(Permission permission) {
         if(permission.getPermissionType().equals(PermissionType.ADD_HOME)) {
             return  new HouseInclusionPermissionOperation(userService,permission);
         }
         return new NonOfPermission();
     }
}
