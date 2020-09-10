package com.mahallem.resource;

import com.mahallem.constants.Sex;
import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.User;
import org.bson.types.ObjectId;

import java.util.Date;

public class UserResource {
    public static UserDetailRequest userDetailRequest = UserDetailRequest.builder()
                                                                         .cellPhone("test")
                                                                         .email("test")
                                                                         .build();

    public static User user = User.builder()
                                  .username("test")
                                  .firstName("test")
                                  .lastName("test")
                                  .birthDate(new Date())
                                  .email("aaa")
                                  .houseId(new ObjectId("5e1a436310c40031d8a7b6d9"))
                                  .build();

    public static UserResponse userResponse = UserResponse.builder()
                                                          .username("test")
                                                          .firstName("test")
                                                          .lastName("test")
                                                          .id("5e1a436310c40031d8a7b6d9")
                                                          .house(HouseResponse.builder()
                                                                              .id("1e1a436310c40031d8a7b6d9")
                                                                              .build())
                                                          .build();

}
