package com.mahallem.Controller;

import com.mahallem.DTO.Request.AnimalRequest;
import com.mahallem.DTO.Response.AnimalResponse;
import com.mahallem.Service.AnimalService;
import com.mahallem.Util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;


    @GetMapping("animal-detail")
    public ResponseEntity<AnimalResponse> getAnimal(@RequestParam String id){
        return new ResponseEntity<>(animalService.getAnimal(id), HttpStatus.OK);
    }


    @PostMapping("add-animal")
    public ResponseEntity<AnimalResponse> setAnimalInformation(@Valid AnimalRequest animalRequest, HttpServletRequest httpServletRequest){
        String id= JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return new ResponseEntity<>(animalService.saveAnimal(id,animalRequest),HttpStatus.CREATED);
    }
}