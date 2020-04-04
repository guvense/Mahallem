package com.mahallem.service;

import com.mahallem.dto.Request.AnimalRequest;
import com.mahallem.dto.Response.AnimalResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnimalService {
    AnimalResponse saveAnimal(String id, AnimalRequest animalRequest);

    Page<AnimalResponse> getAnimals(String id, Pageable pageable);

    AnimalResponse deleteAnimal(String id);
}
