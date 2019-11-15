package com.mahallem.Controller;

import com.mahallem.DTO.Request.AuthRequest;
import com.mahallem.DTO.Response.AuthResponse;
import com.mahallem.DTO.Response.UserResponse;
import com.mahallem.Exception.UserNotFoundException;
import com.mahallem.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    @NotNull
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<AuthResponse> userRegisterController(AuthRequest  authRequest){

       return new ResponseEntity<>( authService.registerUser(authRequest), HttpStatus.OK);

    }
}
