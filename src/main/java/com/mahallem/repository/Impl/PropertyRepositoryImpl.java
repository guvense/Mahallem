package com.mahallem.repository.Impl;

import com.mahallem.entity.HouseProperty;
import com.mahallem.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PropertyRepositoryImpl implements PropertyRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public HouseProperty save(HouseProperty houseProperty) {

        return mongoTemplate.save(houseProperty);
    }

    @Override
    public boolean isPropertyExist(HouseProperty houseProperty) {

        HouseProperty prop = mongoTemplate.findOne(Query.query(Criteria.where("_houseId").is(houseProperty.get_houseId())
                .and("propertyType").is(houseProperty.getPropertyType())), HouseProperty.class);
        return prop != null;
    }
}
