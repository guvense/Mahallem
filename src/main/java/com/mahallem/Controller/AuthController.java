package com.mahallem.Controller;

import com.mahallem.DTO.Request.AuthRequest;
import com.mahallem.DTO.Response.AuthResponse;
import com.mahallem.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    @NotNull
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(@Valid AuthRequest authRequest) {

        return new ResponseEntity<>(authService.registerUser(authRequest), HttpStatus.CREATED);

    }

    @GetMapping("login")
    public ResponseEntity<AuthResponse> login(
            @RequestParam(name = "username") String userName,
            @RequestParam String password) {

        return new ResponseEntity<>(authService.loginUser(userName, password), HttpStatus.OK);

    }
}
