package com.mahallem.Controller;

import com.mahallem.DTO.Request.HouseRequest;
import com.mahallem.DTO.Response.HouseResponse;
import com.mahallem.Service.IHouseService;
import com.mahallem.Util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/house")
@RequiredArgsConstructor
public class HouseController {

    @NotNull
    private final IHouseService houseService;

    @PostMapping("add-house")
    public ResponseEntity<HouseResponse> addHouse(@Valid HouseRequest houseRequest, HttpServletRequest httpServletRequest) {
        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return new ResponseEntity<>(houseService.saveHouse(userId, houseRequest), HttpStatus.CREATED);
    }

    @GetMapping("detail-house")
    public ResponseEntity<HouseResponse> detailHouse(@RequestParam String id) {
        return new ResponseEntity<>(houseService.getHouse(id), HttpStatus.OK);
    }
}

