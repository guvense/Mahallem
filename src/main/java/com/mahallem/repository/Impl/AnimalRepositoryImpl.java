package com.mahallem.repository.Impl;


import com.mahallem.entity.Animal;
import com.mahallem.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AnimalRepositoryImpl implements AnimalRepository {
    @NotNull
    final MongoTemplate mongoTemplate;
    @NotNull
    private final ModelMapper modelMapper;

    @Override
    public Optional<Animal> getAnimal(String id) {
        Animal animal = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), Animal.class);
        return Optional.ofNullable(animal);
    }

    @Override
    public Optional<Animal> getAnimalByHouseId(ObjectId id) {
        Animal animal = mongoTemplate.findOne(Query.query(Criteria.where("houseId").is(id)), Animal.class);
        return Optional.ofNullable(animal);
    }

    @Override
    public Animal save(Animal animal) {
        return mongoTemplate.save(animal);
    }

    @Override
    public Animal delete(ObjectId id) {
        return mongoTemplate.findAndRemove(Query.query(Criteria.where("_id").is(id)), Animal.class);
    }
}
