package com.mahallem.resource;

import com.mahallem.constants.Sex;
import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.User;

public class UserResource {
    public static UserDetailRequest userDetailRequest = UserDetailRequest.builder()
            .age(1)
            .cellPhone("test")
            .email("test")
            .sex(Sex.MALE)
            .build();
    public static User user = User.builder()
            .userName("test")
            .firstName("test")
            .lastName("test")
            .build();

    public static UserResponse userResponse = UserResponse.builder()
            .houseResponse(HouseResource.houseResponse)
            .build();

}
