package com.mahallem.repository;

import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.entity.House;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HouseRepository  {

    Optional<House> getHouse(String id);

    House save(House house);

    HouseResponse getHouseWithProperties(ObjectId id);

}
