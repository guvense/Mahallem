package com.mahallem.resource;

import com.mahallem.constants.PermissionType;
import com.mahallem.dto.Request.PermissionAnswerRequest;
import com.mahallem.dto.Request.PermissionRequest;
import com.mahallem.entity.Permission;
import org.bson.types.ObjectId;

public class PermissionResource {
    public static PermissionRequest housePermissionRequest = PermissionRequest.builder()
            .permissionType(PermissionType.ADD_HOME)
            .toUserName("testUser")
            .build();
    public static PermissionRequest taskPermissionRequest = PermissionRequest.builder()
            .permissionType(PermissionType.ASSIGN_TASK)
            .taskId("5eb8054656d8a15448079ba0")
            .toUserName("testUser")
            .build();

    public static PermissionAnswerRequest housePermissionAnswerRequest = PermissionAnswerRequest.builder()
            .permissionType(PermissionType.ADD_HOME)
            .fromUserId("5eb6a52256d8a1577c49621d")
            .build();

    public static PermissionAnswerRequest taskPermissionAnswerRequest = PermissionAnswerRequest.builder()
            .permissionType(PermissionType.ASSIGN_TASK)
            .fromUserId("5eb6a52256d8a1577c49621d")
            .taskId("5eb8054656d8a15448079ba0")
            .build();

    public static Permission permission = Permission.builder()
            .fromUserId(new ObjectId("5eb6a52256d8a1577c49621d"))
            .toUserId(new ObjectId("5eb6a49f56d8a1577c49621b"))
            .build();


}
