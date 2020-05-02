package com.mahallem.controller;

import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.service.UserService;
import com.mahallem.util.JwtUtil;
import com.mahallem.util.ResponseUtil;
import com.mahallem.viewmodel.MainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("homemates")
    public ResponseEntity<MainResponse<List<UserResponse>>> homemates(HttpServletRequest httpServletRequest){
        String userId=JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return ResponseUtil.data(userService.getHomemates(userId));
    }

}
