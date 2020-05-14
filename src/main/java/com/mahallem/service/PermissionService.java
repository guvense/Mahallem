package com.mahallem.service;


import com.mahallem.dto.Request.PermissionAnswerRequest;
import com.mahallem.dto.Request.PermissionRequest;
import com.mahallem.dto.Response.PermissionResponse;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissionService {

    PermissionResponse createUserToHouseRequest(String userId, PermissionRequest permissionRequest);

    Page<PermissionResponse> getAllPendingPermissionRequest(String userId, Pageable pageable);

    UserResponse approvePermissionRequest(String userId, PermissionAnswerRequest permissionAnswerRequest);

    UserResponse rejectPermissionRequest(String userId, PermissionAnswerRequest permissionAnswerRequest);

    PermissionResponse assignTaskToUser(String userId, PermissionRequest permissionRequest);

}
