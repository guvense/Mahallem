package com.mahallem.repository.Impl;

import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.entity.User;
import com.mahallem.exception.UserUpdateException;
import com.mahallem.repository.UserRepository;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    @NotNull
    final MongoTemplate mongoTemplate;

    @Override
    public User save(User user) {

        return mongoTemplate.save(user);
    }

    @Override
    public Optional<User> findBy_id(ObjectId id) {
        return Optional.ofNullable(mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), User.class));

    }

    @Override
    public Optional<User> findByUserName(String username) {
        return Optional.ofNullable(mongoTemplate.findOne(Query.query(Criteria.where("userName").is(username)), User.class));

    }

    public void updateUserDetailInfo(String userId, UserDetailRequest userDetailRequest) {
        UpdateResult updateResult = mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(userId)),
                new Update()
                        .set("age", userDetailRequest.getAge())
                        .set("cellPhone", userDetailRequest.getCellPhone())
                        .set("email", userDetailRequest.getEmail())
                        .set("sex", userDetailRequest.getSex()), User.class);

        if (!updateResult.wasAcknowledged()) {
            throw new UserUpdateException();
        }
    }

    @Override
    public void addHouseIdToUser(String userId, ObjectId houseId) {
        UpdateResult updateResult = mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(userId)),
                new Update()
                        .set("houseId", houseId), User.class);

        if (!updateResult.wasAcknowledged()) {
            throw new UserUpdateException();
        }
    }

    @Override
    public Optional<User> getUserInfo(String id) {
        AggregationOperation matchOperation = Aggregation.match(Criteria.where("_id").is(new ObjectId(id)));

        Aggregation aggregation = Aggregation.newAggregation(
                matchOperation,
                lookUpUserToHouse(),
                lookUpHouseToAnimal());
        User user1 = mongoTemplate.aggregate(aggregation, "user", User.class).getUniqueMappedResult();
        Optional<User> user = Optional.ofNullable(user1);
    return user;
    }


    private LookupOperation lookUpUserToHouse() {

        return LookupOperation.newLookup()
                .from("house")
                .localField("houseId")
                .foreignField("_id")
                .as("house");

    }

    private LookupOperation lookUpHouseToAnimal() {
        return LookupOperation.newLookup()
                .from("animal")
                .localField("houseId")
                .foreignField("houseId")
                .as("animals");
    }


}
