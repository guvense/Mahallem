package com.mahallem.Service;

import com.mahallem.DTO.Request.UserDetailRequest;
import com.mahallem.DTO.Response.UserResponse;

public interface IUserService {

    UserResponse setUserDetailInformation(String userId, UserDetailRequest userDetailRequest);

    UserResponse userInfo(String userId);
}
