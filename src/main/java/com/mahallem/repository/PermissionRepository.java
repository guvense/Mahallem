package com.mahallem.repository;

import com.mahallem.constants.PermissionType;
import com.mahallem.entity.Permission;
import org.bson.types.ObjectId;

public interface PermissionRepository {

    Permission save(Permission permission);

    Permission getPermission(ObjectId fromUserId, ObjectId toUserId, PermissionType permissionType);
}
