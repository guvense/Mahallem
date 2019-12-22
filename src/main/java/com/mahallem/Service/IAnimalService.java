package com.mahallem.Service;

import com.mahallem.DTO.Request.AnimalRequest;
import com.mahallem.DTO.Response.AnimalResponse;
import org.bson.types.ObjectId;

public interface IAnimalService {
    AnimalResponse saveAnimal(String id, AnimalRequest animalRequest);
    AnimalResponse getAnimal(String id);
}
