package com.mahallem.controller;

import com.mahallem.customize.Annotation.ApiPageable;
import com.mahallem.dto.Request.AnimalRequest;
import com.mahallem.dto.Response.AnimalResponse;
import com.mahallem.service.AnimalService;
import com.mahallem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping
    @ApiPageable
    public ResponseEntity<Page<AnimalResponse>> getAnimal(Pageable pageable, HttpServletRequest httpServletRequest){
        String userId= JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return new ResponseEntity<>(animalService.getAnimals(userId,pageable), HttpStatus.OK);
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