package com.mahallem.service;

import com.mahallem.dto.Request.AuthRequest;
import com.mahallem.dto.Response.AuthResponse;


public interface AuthService {

    AuthResponse registerUser(AuthRequest authRequest);

    AuthResponse loginUser(String userName, String password);
}
