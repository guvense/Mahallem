package com.mahallem.service;


import com.mahallem.dto.Request.PermissionRequest;
import com.mahallem.dto.Response.PermissionResponse;
import com.mahallem.entity.Permission;

public interface PermissionService {

    PermissionResponse createUserToHouseRequest(String userId, PermissionRequest permissionRequest);
}
