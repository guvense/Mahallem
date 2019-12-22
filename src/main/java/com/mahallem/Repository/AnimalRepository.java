package com.mahallem.Repository;

import com.mahallem.Entity.Animal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AnimalRepository  extends MongoRepository<Animal, ObjectId> {
    Optional<Animal> findBy_id(String id);
}
