package com.mahallem.service.Impl;

import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.User;
import com.mahallem.exception.UserNotFoundException;
import com.mahallem.mapper.UserMapper;
import com.mahallem.repository.UserRepository;
import com.mahallem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @NotNull
    final MongoTemplate mongoTemplate;

    @Override
    public UserResponse setUserDetailInformation(String userId, UserDetailRequest userDetailRequest) {

        userRepository.updateUserDetailInfo(userId, userDetailRequest);
        User user = userRepository.findById(new ObjectId(userId))
                                  .orElseThrow(UserNotFoundException::new);
        return UserMapper.map.userToUserResponse(user);

    }

    @Override
    public UserResponse userInfo(String userId) {

        User user = userRepository.getUserInfo(userId)
                                  .orElseThrow(UserNotFoundException::new);
        return UserMapper.map.userToUserResponse(user);
    }

    @Override
    public UserResponse getUser(String userId) {
        User user = userRepository.getUserInfo(userId)
                                  .orElseThrow(UserNotFoundException::new);

        return UserMapper.map.userToUserResponse(user);
    }

    @Override
    public void addHouseIdToUser(String userId, ObjectId houseId) {

        userRepository.addHouseIdToUser(userId, houseId);
    }

    @Override
    public String getHouseId(String userId) {

        return getUser(userId).getHouseResponse().getId();
    }


    public Long countAllUsers() {
        return userRepository.countAllUsers();
    }

    @Override
    public List<UserResponse> getHomemates(String userId) {
        User user = userRepository.getUserInfo(userId)
                .orElseThrow(UserNotFoundException::new);
        List<User> homemates=userRepository.findByHouseId(userId,user.getHouseId());
        List<UserResponse> userResponseList = UserMapper.map.userListToUserResponseList(homemates);
        return userResponseList;
    }
}

