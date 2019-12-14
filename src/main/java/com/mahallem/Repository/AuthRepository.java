package com.mahallem.Repository;

import com.mahallem.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findBy_id(ObjectId id);
    Optional<User> findByUserName(String username);

}
