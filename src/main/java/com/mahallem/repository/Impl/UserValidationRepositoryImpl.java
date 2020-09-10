package com.mahallem.repository.Impl;

import com.mahallem.dto.Request.UserValidationRequest;
import com.mahallem.entity.Task;
import com.mahallem.entity.User;
import com.mahallem.entity.UserValidation;
import com.mahallem.repository.UserValidationRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class UserValidationRepositoryImpl implements UserValidationRepository {

    @NotNull
    private final MongoTemplate mongoTemplate;

    @Override
    public UserValidation save(UserValidation userValidation) {
        return mongoTemplate.save(userValidation);
    }

    @Override
    public Optional<UserValidation> findByUserId(ObjectId userId) {
        return Optional.ofNullable(mongoTemplate.findOne(Query.query(Criteria.where("user_id").is(userId)), UserValidation.class));
    }

    @Override
    public void updateEmailVerification(ObjectId userId, Boolean isEmailVerified) {
        Update update = new Update().set("isEmailVerified", isEmailVerified);
        mongoTemplate.updateFirst(Query.query(Criteria.where("user_id").is(userId)), update, UserValidation.class);
    }
}
