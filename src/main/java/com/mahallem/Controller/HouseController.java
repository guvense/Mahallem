package com.mahallem.Controller;

import com.mahallem.DTO.Request.HouseRequest;
import com.mahallem.DTO.Response.HouseResponse;
import com.mahallem.Service.IHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/house")
@RequiredArgsConstructor
public class HouseController {

    @NotNull
    private final IHouseService houseService;

    @PostMapping("add_house")
    public ResponseEntity<HouseResponse> add_house(@Valid HouseRequest houseRequest) {

        return new ResponseEntity<>(houseService.saveHouse(houseRequest), HttpStatus.OK);

    }

    @GetMapping("detail_house")
    public ResponseEntity<HouseResponse> detail_house(@RequestParam(name = "name") String name) {
        return new ResponseEntity<>(houseService.getHouse(name), HttpStatus.OK);
    }
}

