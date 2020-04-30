package com.mahallem.service;

import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.dto.Response.UserResponse;
import org.bson.types.ObjectId;

public interface UserService {

    UserResponse setUserDetailInformation(String userId, UserDetailRequest userDetailRequest);

    UserResponse userInfo(String userId);

    UserResponse getUser(String userId);

    void addHouseIdToUser(String userId, ObjectId houseId);

    String getHouseId(String userId);

    Integer countAllUsers();
}
