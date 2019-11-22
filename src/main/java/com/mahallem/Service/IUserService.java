package com.mahallem.Service;

import com.mahallem.DTO.Request.UserDetailRequest;
import com.mahallem.DTO.Response.UserResponse;
import org.bson.types.ObjectId;

public interface IUserService {

    UserResponse setUserDetailInformation(String userId, UserDetailRequest userDetailRequest);
}
