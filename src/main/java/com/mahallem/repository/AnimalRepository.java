package com.mahallem.repository;

import com.mahallem.entity.Animal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AnimalRepository  extends MongoRepository<Animal, ObjectId> {
    Optional<Animal> findById(String id);
}
