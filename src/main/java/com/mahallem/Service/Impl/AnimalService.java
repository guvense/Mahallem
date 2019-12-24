package com.mahallem.Service.Impl;

import com.mahallem.DTO.Request.AnimalRequest;
import com.mahallem.DTO.Response.AnimalResponse;
import com.mahallem.DTO.Response.HouseResponse;
import com.mahallem.DTO.Response.UserResponse;
import com.mahallem.Entity.Animal;
import com.mahallem.Entity.House;
import com.mahallem.Exception.AnimalNotFoundException;
import com.mahallem.Repository.AnimalRepository;
import com.mahallem.Repository.HouseRepository;
import com.mahallem.Service.HouseService;
import com.mahallem.Service.UserService;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService implements com.mahallem.Service.AnimalService {

    @NotNull
    private final ModelMapper modelMapper;

    @NotNull
    private final AnimalRepository animalRepository;

    @NotNull
    private final HouseService houseService;

    @NotNull
    private final UserService userService;

    @NotNull
    private final HouseRepository houseRepository;

    private final MongoTemplate mongoTemplate;


    @Override
    public AnimalResponse saveAnimal(String id, AnimalRequest animalRequest) {
        final Animal animal = modelMapper.map(animalRequest, Animal.class);
        Animal savedAnimal = animalRepository.save(animal);
        boolean b = updateAnimalList(id, animal);
        return modelMapper.map(savedAnimal, AnimalResponse.class);
    }

    @Override
    public AnimalResponse getAnimal(String id) {
        Optional<Animal> optionalAnimal = animalRepository.findBy_id(id);
        Animal animal = optionalAnimal.orElseThrow(AnimalNotFoundException::new);
        return modelMapper.map(animal, AnimalResponse.class);
        
    }

    private boolean updateAnimalList(String id, Animal animal) {
        UserResponse userResponse = userService.userInfo(id);
        HouseResponse houseResponse = houseService.getHouse(userResponse.getHouseId().toString());
        House house = modelMapper.map(houseResponse, House.class);
        UpdateResult updateResult = mongoTemplate.updateFirst(
                Query.query(Criteria.where("house").
                is(house.get_id())), new Update().push("_animalIds", animal.get_id()), House.class);
        
        return updateResult.wasAcknowledged();
    }

}
