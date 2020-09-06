package com.mahallem.resource;

import com.mahallem.dto.Request.AuthRequest;

public class AuthResource {

    public static AuthRequest authRequest = AuthRequest.builder()
            .firstName("test")
            .lastName("test")
            .username("test")
            .email("sss")
            .build();

    public static AuthRequest authRequest2 = AuthRequest.builder()
            .firstName("kkk")
            .lastName("kk")
            .username("kk")
            .email("aaa")
            .build();

}
