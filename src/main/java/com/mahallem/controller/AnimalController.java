package com.mahallem.controller;

import com.mahallem.dto.Request.AnimalRequest;
import com.mahallem.dto.Response.AnimalResponse;
import com.mahallem.service.AnimalService;
import com.mahallem.util.JwtUtil;
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

    @GetMapping
    public ResponseEntity<AnimalResponse> getAnimal(HttpServletRequest httpServletRequest){
        String id= JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return new ResponseEntity<>(animalService.getAnimal(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnimalResponse> setAnimalInformation(@Valid AnimalRequest animalRequest, HttpServletRequest httpServletRequest){
        String id= JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return new ResponseEntity<>(animalService.saveAnimal(id,animalRequest),HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<AnimalResponse> deleteAnimal(String animalId, HttpServletRequest httpServletRequest){
        String id= JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return new ResponseEntity<>(animalService.deleteAnimal(animalId),HttpStatus.OK);
    }

}