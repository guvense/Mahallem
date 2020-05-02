package com.mahallem.controller;

import com.mahallem.customize.Annotation.ApiPageable;
import com.mahallem.dto.Request.PermissionAnswerRequest;
import com.mahallem.dto.Request.PermissionRequest;
import com.mahallem.dto.Response.PermissionResponse;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.service.PermissionService;
import com.mahallem.util.JwtUtil;
import com.mahallem.util.ResponseUtil;
import com.mahallem.viewmodel.MainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping("user-to-house")
    public ResponseEntity<MainResponse<Object>> addUserToHouseRequestCreate(@Valid @RequestBody PermissionRequest permissionRequest, HttpServletRequest httpServletRequest) {

        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return ResponseUtil.data(permissionService.createUserToHouseRequest(userId, permissionRequest));
    }
    @ApiPageable
    @GetMapping
    public ResponseEntity<Page<PermissionResponse>> getAllPendingPermissionRequests(Pageable pageable, HttpServletRequest httpServletRequest) {

        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return ResponseUtil.data(permissionService.getAllPendingPermissionRequest(userId,pageable));
    }

    @PostMapping("approve")
    public ResponseEntity<MainResponse<UserResponse>> approvePermission(@Valid @RequestBody PermissionAnswerRequest permissionAnswerRequest, HttpServletRequest httpServletRequest) {
        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return ResponseUtil.data(permissionService.approvePermissionRequest(userId,permissionAnswerRequest));

    }

}
