package com.mahallem.repository;

import com.mahallem.constants.PermissionStatus;
import com.mahallem.constants.PermissionType;
import com.mahallem.dto.Request.PermissionRequest;
import com.mahallem.entity.Permission;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository {

    Permission save(Permission permission);

    Optional<Permission> getPermission(ObjectId fromUserId, ObjectId toUserId, Permission permission);

    List<Permission> getAllPendingPermissions(String userId, Pageable pageable);

    Boolean setPermissionStatus(Permission permission, PermissionStatus permissionStatus);
}
