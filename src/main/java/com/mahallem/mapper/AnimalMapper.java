package com.mahallem.mapper;

import com.mahallem.dto.Request.AnimalRequest;
import com.mahallem.dto.Response.AnimalResponse;
import com.mahallem.entity.Animal;
import com.mahallem.mapper.customize.ObjectIdMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ObjectIdMapper.class)
public interface AnimalMapper {

    AnimalMapper map = Mappers.getMapper(AnimalMapper.class);

    Animal animalRequestToAnimal(AnimalRequest animalRequest);

    AnimalResponse animalToAnimalResponse(Animal animal);

    List<AnimalResponse> animalToAnimalResponseList(List<Animal> animal);
}
