package com.mahallem.repository;

import com.mahallem.dto.Request.UserValidationRequest;
import com.mahallem.entity.UserValidation;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface UserValidationRepository {

    UserValidation save(UserValidation userValidation);

    Optional<UserValidation> findByUserId(ObjectId id);

    void updateEmailVerification(ObjectId userId, Boolean isEmailVerified);
}
