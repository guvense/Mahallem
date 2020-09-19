package com.mahallem.controller;

import com.mahallem.dto.Request.HouseRequest;
import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.service.HouseService;
import com.mahallem.util.JwtUtil;
import com.mahallem.util.ResponseUtil;
import com.mahallem.viewmodel.MainResponse;
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
    public ResponseEntity<MainResponse<HouseResponse>> addHouse(@Valid @RequestBody HouseRequest houseRequest, HttpServletRequest httpServletRequest) {

        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        HouseResponse houseResponse = houseService.saveHouse(userId, houseRequest);
        return ResponseUtil.data(houseResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<MainResponse<HouseResponse>> detailHouse(HttpServletRequest httpServletRequest) {
        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return ResponseUtil.data(houseService.getHouse(userId));
    }

    @PatchMapping
    public ResponseEntity<MainResponse<HouseResponse>> updateHouse(
            @Valid @RequestBody HouseRequest houseRequest, HttpServletRequest httpServletRequest){

        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return ResponseUtil.data(houseService.updateHouse(userId, houseRequest));
    }
}

