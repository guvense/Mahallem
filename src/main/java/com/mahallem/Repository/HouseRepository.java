package com.mahallem.Repository;

import com.mahallem.Entity.House;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HouseRepository extends MongoRepository<House, ObjectId> {

    Optional<House> findBy_id(String id);

}
