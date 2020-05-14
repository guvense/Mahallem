package com.mahallem.repository;

import com.mahallem.constants.PermissionType;
import com.mahallem.entity.Permission;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PermissionRepository {

    Permission save(Permission permission);

    Permission getPermission(ObjectId fromUserId, ObjectId toUserId, PermissionType permissionType);

    Permission getPermission(ObjectId fromUserId, ObjectId toUserId, PermissionType permissionType,ObjectId taskId);

    List<Permission> getAllPendingPermissions(ObjectId userId, Pageable pageable);

    Boolean setPermissionStatusToApprove(Permission permission);
}