package com.mahallem.repository;

import com.mahallem.entity.Animal;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface AnimalRepository{
    Optional<Animal> getAnimal(String id);
    Optional<Animal> getAnimalByHouseId(ObjectId id);
    Animal save(Animal animal);
    Animal delete (ObjectId id);

}
