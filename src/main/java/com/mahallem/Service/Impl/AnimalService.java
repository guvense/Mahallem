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
import com.mahallem.Service.IAnimalService;
import com.mahallem.Service.IHouseService;
import com.mahallem.Service.IUserService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import com.mongodb.client.result.UpdateResult;
import com.oracle.deploy.update.Updater;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.net.UnknownHostException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService implements IAnimalService {

    @NotNull
    private final ModelMapper modelMapper;

    @NotNull
    private final AnimalRepository animalRepository;

    @NotNull
    private final IHouseService houseService;

    @NotNull
    private  final IUserService userService;

    @NotNull
    private final HouseRepository houseRepository;

    private final MongoTemplate mongoTemplate;


    @Override
    public AnimalResponse saveAnimal(String id, AnimalRequest animalRequest) {
        final Animal animal=modelMapper.map(animalRequest,Animal.class);
        Animal savedAnimal=animalRepository.save(animal);
        updateAnimalList(id,animal);
        AnimalResponse animalResponse=modelMapper.map(savedAnimal,AnimalResponse.class);
        return animalResponse;
    }

    @Override
    public AnimalResponse getAnimal(String id) {
        Optional<Animal> optionalAnimal=animalRepository.findBy_id(id);
        Animal animal= optionalAnimal.orElseThrow(AnimalNotFoundException::new);
        AnimalResponse animalResponse=modelMapper.map(animal,AnimalResponse.class);
        return null;
    }

    private void updateAnimalList(String id,Animal animal){
        UserResponse userResponse=userService.userInfo(id);
        HouseResponse houseResponse= houseService.getHouse(userResponse.get_houseId().toString());
        House house =modelMapper.map(houseResponse,House.class);
        UpdateResult updateResult = mongoTemplate.updateFirst(Query.query(Criteria.where("house").is(house.get_id())), new Update().push("_animalIds", animal.get_id()), House.class);
    }

}
