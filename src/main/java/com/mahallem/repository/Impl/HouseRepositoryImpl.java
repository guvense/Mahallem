package com.mahallem.repository.Impl;

import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.entity.House;
import com.mahallem.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HouseRepositoryImpl implements HouseRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<House> getHouse(String id) {
        House house = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), House.class);
        return Optional.ofNullable(house);
    }

    @Override
    public House save(House house) {
        return mongoTemplate.save(house);
    }

    @Override
    public HouseResponse getHouseWithProperties(ObjectId id) {
        AggregationOperation match= Aggregation.match(Criteria.where("_id").is(id));
        Aggregation aggregation = Aggregation.newAggregation(match,
                lookupHouseToProperty());
        HouseResponse houseResponse = mongoTemplate.aggregate(aggregation, "house", HouseResponse.class).getUniqueMappedResult();
        return houseResponse;

    }

    private LookupOperation lookupHouseToProperty() {
        return LookupOperation.newLookup()
                .from("houseProperty")
                .localField("_id")
                .foreignField("_houseId")
                .as("properties");
    }
}
