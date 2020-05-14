package com.mahallem.repository;

import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.entity.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;


public interface UserRepository {

    Optional<User> findById(ObjectId id);

    Optional<User> findByUserName(String username);

    void updateUserDetailInfo(String userId, UserDetailRequest userDetailRequest);

    void addHouseIdToUser(String userId, ObjectId houseId);

    Optional<User> getUserInfo(String id);

    User save(User user);

    Long countAllUsers();

    List<User> findHomematesByHouseId(ObjectId hostUserId, ObjectId houseId);

    Optional<User> getUserInfoFromUsername(String username);

}
