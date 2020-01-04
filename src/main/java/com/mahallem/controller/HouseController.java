package com.mahallem.controller;

import com.mahallem.dto.Request.HouseRequest;
import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.service.HouseService;
import com.mahallem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/house")
@RequiredArgsConstructor
public class HouseController {

    @NotNull
    private final HouseService houseService;

    @PostMapping("add-house")
    public ResponseEntity<HouseResponse> addHouse(@Valid HouseRequest houseRequest, HttpServletRequest httpServletRequest) {
        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return new ResponseEntity<>(houseService.saveHouse(userId, houseRequest), HttpStatus.CREATED);
    }

    @GetMapping("detail-house/{id}")
    public ResponseEntity<HouseResponse> detailHouse(@PathVariable String id) {
        return new ResponseEntity<>(houseService.getHouse(id), HttpStatus.OK);
    }
}

