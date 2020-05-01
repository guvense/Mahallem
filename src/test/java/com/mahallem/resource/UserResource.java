package com.mahallem.resource;

import com.mahallem.constants.Sex;
import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.entity.User;
import org.bson.types.ObjectId;

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
            .houseId(new ObjectId("5e1a436310c40031d8a7b6d9"))
            .build();

}
