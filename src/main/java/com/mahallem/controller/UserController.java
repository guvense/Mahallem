package com.mahallem.controller;

import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.dto.Response.AuthResponse;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.service.UserService;
import com.mahallem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("add-user-detail")
    public ResponseEntity<UserResponse> addUserDetail(@Valid UserDetailRequest userDetailRequest, HttpServletRequest httpServletRequest) {

        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return new ResponseEntity<>(userService.setUserDetailInformation(userId, userDetailRequest), HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<UserResponse> userInfo(HttpServletRequest httpServletRequest) {

        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);

        return new ResponseEntity<>(userService.userInfo(userId), HttpStatus.OK);

    }

}
