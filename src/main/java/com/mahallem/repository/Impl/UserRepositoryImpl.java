package com.mahallem.repository.Impl;

import com.mahallem.constants.Status;
import com.mahallem.entity.User;
import com.mahallem.exception.ProfilePictureUpdateException;
import com.mahallem.exception.UserUpdateException;
import com.mahallem.repository.UserRepository;
import com.mahallem.util.QueryUtil;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
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

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    @NotNull
    private final MongoTemplate mongoTemplate;

     private final QueryUtil queryUtil;

    @Override
    public User save(User user) {
        return mongoTemplate.save(user);
    }

    @Override
    public Optional<User> findById(ObjectId id) {
        return Optional.ofNullable(mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), User.class));

    }

    @Override
    public Long countAllUsers(){
       return mongoTemplate.count(Query.query(Criteria.where("status").is(Status.ACTIVE)), User.class);

    }

    @Override
    public Optional<User> findByUserName(String username) {
        return Optional.ofNullable(mongoTemplate.findOne(Query.query(Criteria.where("username").is(username)), User.class));

    }

    public void updateUserDetailInfo(String userId, User user) {

        Update update = queryUtil.generateUpdateQuery(user, "create_date");
        UpdateResult updateResult = mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").is(userId)),
                update,
                User.class);

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
    public List<User> findHomematesByHouseId(ObjectId hostUserId, ObjectId houseId) {
        List<User> users=mongoTemplate.find(Query.query(Criteria.where("houseId").is(houseId).and("_id").ne(hostUserId)), User.class);
        return users;
    }

    @Override
    public Optional<User> getUserInfoFromUsername(String username) {
        return Optional.ofNullable(mongoTemplate.findOne(Query.query(Criteria.where("username").is(username)), User.class));
    }

    @Override
    public void uploadProfilePicture(String url, ObjectId id) {
        UpdateResult updateResult = mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(id)),
                new Update().set("profilePictureURL", url), User.class);

        if (!updateResult.wasAcknowledged()) {
            throw new ProfilePictureUpdateException();
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
                .localField("house_id")
                .foreignField("_id")
                .as("house");

    }

    private LookupOperation lookUpHouseToAnimal() {
        return LookupOperation.newLookup()
                .from("animal")
                .localField("house_id")
                .foreignField("houseId")
                .as("animals");
    }


}
