package com.mahallem.resource;

import com.mahallem.dto.Request.AuthRequest;

public class AuthResource {

    public static AuthRequest authRequest = AuthRequest.builder()
            .firstName("test")
            .lastName("test")
            .userName("test")
            .build();

}
