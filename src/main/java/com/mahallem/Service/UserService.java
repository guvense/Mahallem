package com.mahallem.Service;

import com.mahallem.DTO.Request.UserDetailRequest;
import com.mahallem.DTO.Response.UserResponse;
import org.bson.types.ObjectId;

public interface UserService {

    UserResponse setUserDetailInformation(String userId, UserDetailRequest userDetailRequest);

    UserResponse userInfo(String userId);

    UserResponse getUser(String userId);

    void addHouseIdToUser(String userId, ObjectId houseId);
}
