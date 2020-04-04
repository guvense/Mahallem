package com.mahallem.repository.Impl;


import com.mahallem.entity.Animal;
import com.mahallem.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AnimalRepositoryImpl implements AnimalRepository {

    @NotNull
    private final MongoTemplate mongoTemplate;

    @Override
    public List<Animal> getAnimals(ObjectId houseId, Pageable pageable) {
        return mongoTemplate.find(Query.query(Criteria.where("houseId").is(houseId)).with(pageable), Animal.class);

    }

    @Override
    public Optional<Animal> getAnimalByHouseId(ObjectId animalHouseId) {
        Animal animal = mongoTemplate.findOne(Query.query(Criteria.where("houseId").is(animalHouseId)), Animal.class);
        return Optional.ofNullable(animal);
    }

    @Override
    public Animal save(Animal animal) {
        return mongoTemplate.save(animal);
    }

    @Override
    public Animal delete(ObjectId animalId) {
        return mongoTemplate.findAndRemove(Query.query(Criteria.where("_id").is(animalId)), Animal.class);
    }
}
