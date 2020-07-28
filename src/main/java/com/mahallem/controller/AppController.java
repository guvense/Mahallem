package com.mahallem.controller;

import com.mahallem.config.AppConfig;
import com.mahallem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/app-info")
@RequiredArgsConstructor
public class AppController {

    @NotNull
    private final AppConfig appConfig;
    private final UserService userService;

    @GetMapping("version")
    public ResponseEntity<String> version() {

        return new ResponseEntity<>(appConfig.getVersion(), HttpStatus.OK);

    }

    @GetMapping("active-users")
    public Long getActiveUser(){
        return userService.countAllUsers();
    }
}
