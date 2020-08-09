package com.mahallem.controller;

import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.service.UserService;
import com.mahallem.util.JwtUtil;
import com.mahallem.util.ResponseUtil;
import com.mahallem.viewmodel.MainResponse;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("add-user-detail")
    public ResponseEntity<MainResponse<UserResponse>> addUserDetail(@Valid @RequestBody UserDetailRequest userDetailRequest, HttpServletRequest httpServletRequest) {

        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        UserResponse userResponse = userService.setUserDetailInformation(userId, userDetailRequest);
        return ResponseUtil.data(userResponse);
    }

    @GetMapping
    public ResponseEntity<MainResponse<UserResponse>> userInfo(HttpServletRequest httpServletRequest) {

        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return ResponseUtil.data(userService.userInfo(userId));
    }

    @GetMapping("homemate")
    public ResponseEntity<MainResponse<List<UserResponse>>> homemates(HttpServletRequest httpServletRequest) {
        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return ResponseUtil.data(userService.getHomemates(userId));
    }

    @PostMapping("upload-profile-picture")
    public ResponseEntity<MainResponse<UserResponse>> writeBlobFile(@RequestParam(value = "image", required = false) MultipartFile file, HttpServletRequest httpServletRequest) throws IOException {
        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return ResponseUtil.data(userService.uploadProfilePicture(file, userId));
    }
}
