package com.mahallem.service;

import com.mahallem.dto.Request.AnimalRequest;
import com.mahallem.dto.Response.AnimalResponse;

public interface AnimalService {
    AnimalResponse saveAnimal(String id, AnimalRequest animalRequest);

    AnimalResponse getAnimal(String id);

    AnimalResponse deleteAnimal(String id, String userId);
}
