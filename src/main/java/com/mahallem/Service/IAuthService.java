package com.mahallem.Service;

import com.mahallem.DTO.Request.AuthRequest;
import com.mahallem.DTO.Response.AuthResponse;


public interface IAuthService {

    AuthResponse registerUser(AuthRequest authRequest);

    AuthResponse loginUser(String userName, String password);
}
