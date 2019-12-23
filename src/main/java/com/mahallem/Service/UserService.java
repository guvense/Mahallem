package com.mahallem.Service;

import com.mahallem.DTO.Request.UserDetailRequest;
import com.mahallem.DTO.Response.UserResponse;

public interface UserService {

    UserResponse setUserDetailInformation(String userId, UserDetailRequest userDetailRequest);

    UserResponse userInfo(String userId);

    void addHouseIdToUser(String userId, String houseId);
}
