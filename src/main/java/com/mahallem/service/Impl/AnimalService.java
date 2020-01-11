package com.mahallem.service.Impl;

import com.mahallem.dto.Request.AnimalRequest;
import com.mahallem.dto.Response.AnimalResponse;
import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.Animal;
import com.mahallem.entity.House;
import com.mahallem.exception.AnimalNotFoundException;
import com.mahallem.repository.AnimalRepository;
import com.mahallem.repository.HouseRepository;
import com.mahallem.service.HouseService;
import com.mahallem.service.UserService;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
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
public class AnimalService implements com.mahallem.service.AnimalService {

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
    public AnimalResponse saveAnimal(String userId, AnimalRequest animalRequest) {
        final Animal animal = modelMapper.map(animalRequest, Animal.class);
        String houseId = userService.getUser(userId).getHouseResponse().getId();
        animal.setHouseId(new ObjectId(houseId));
        Animal savedAnimal = animalRepository.save(animal);
        return modelMapper.map(savedAnimal, AnimalResponse.class);
    }

    @Override
    public AnimalResponse getAnimal(String id) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);
        Animal animal = optionalAnimal.orElseThrow(AnimalNotFoundException::new);
        return modelMapper.map(animal, AnimalResponse.class);
        
    }

    private boolean updateAnimalList(String id, Animal animal) {
        UserResponse userResponse = userService.getUser(id);
        HouseResponse houseResponse = houseService.getHouse(userResponse.getHouseResponse().getId());
        House house = modelMapper.map(houseResponse, House.class);
        UpdateResult updateResult = mongoTemplate.updateFirst(
                Query.query(Criteria.where("house").
                is(house.getId())), new Update().push("_animalIds", animal.getId()), House.class);
        
        return updateResult.wasAcknowledged();
    }

}
