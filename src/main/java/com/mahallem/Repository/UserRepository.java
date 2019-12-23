package com.mahallem.Repository;

import com.mahallem.DTO.Request.UserDetailRequest;
import com.mahallem.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository {

    Optional<User> findBy_id(ObjectId id);

    Optional<User> findByUserName(String username);

    void updateUserDetailInfo(String userId, UserDetailRequest userDetailRequest);

    void addHouseIdToUser(String userId, String houseId);

    Optional<User> getUserInfo(String id);

    User save(User user);

}
