package com.mahallem.repository;

import com.mahallem.entity.Animal;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository {
    List<Animal> getAnimals(ObjectId houseId, Pageable pageable);

    Optional<Animal> getAnimalByHouseId(ObjectId animalHouseid);

    Animal save(Animal animal);

    Animal delete(ObjectId animalId);
}
