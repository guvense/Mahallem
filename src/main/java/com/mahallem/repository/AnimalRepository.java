package com.mahallem.repository;

import com.mahallem.entity.Animal;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface AnimalRepository {
    Optional<Animal> getAnimal(String animalId);

    Optional<Animal> getAnimalByHouseId(ObjectId animalHouseid);

    Animal save(Animal animal);

    Animal delete(ObjectId animalId);
}
