package com.mahallem.Service;

import com.mahallem.DTO.Request.AuthRequest;
import com.mahallem.DTO.Response.UserResponse;

public interface AuthService {

    UserResponse registerUser(AuthRequest authRequest);
}
