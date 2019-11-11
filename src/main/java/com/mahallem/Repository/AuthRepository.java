package com.mahallem.Repository;

import com.mahallem.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<User, ObjectId> {


}
