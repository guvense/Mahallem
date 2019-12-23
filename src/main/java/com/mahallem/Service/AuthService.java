package com.mahallem.Service;

import com.mahallem.DTO.Request.AuthRequest;
import com.mahallem.DTO.Response.AuthResponse;


public interface AuthService {

    AuthResponse registerUser(AuthRequest authRequest);

    AuthResponse loginUser(String userName, String password);
}
