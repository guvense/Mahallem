package com.mahallem.controller;

import com.mahallem.config.AppConfig;
import com.mahallem.dto.Response.AuthResponse;
import com.mahallem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/app-info")
@RequiredArgsConstructor
public class AppController {

    @NotNull
    private final AppConfig appConfig;

    @GetMapping("version")
    public ResponseEntity<String> version() {

        return new ResponseEntity<>(appConfig.getVersion(), HttpStatus.OK);

    }
}
