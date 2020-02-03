package com.mahallem.resource;

import com.mahallem.constants.AnimalSex;
import com.mahallem.constants.AnimalType;
import com.mahallem.dto.Request.AnimalRequest;

import java.util.Date;

public class AnimalResource {

   public static AnimalRequest animalRequest= AnimalRequest.builder()
                                                           .type(AnimalType.BIRD)
                                                           .sex(AnimalSex.MALE)
                                                           .birthDate(new Date())
                                                           .build();

}
