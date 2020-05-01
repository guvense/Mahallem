package com.mahallem.resource;

import com.mahallem.constants.AnimalSex;
import com.mahallem.constants.AnimalType;
import com.mahallem.dto.Request.AnimalRequest;
import com.mahallem.entity.Animal;
import org.bson.types.ObjectId;

import java.util.Date;

public class AnimalResource {

    public static AnimalRequest animalRequest = AnimalRequest.builder()
            .type(AnimalType.BIRD)
            .sex(AnimalSex.MALE)
            .birthDate(new Date())
            .build();
    public static Animal animal = Animal.builder()
            .sex(AnimalSex.MALE)
            .type(AnimalType.BIRD)
            .houseId(new ObjectId("5e1a436310c40031d8a7b6d9"))
            .birthDate(new Date())
            .build();
}
