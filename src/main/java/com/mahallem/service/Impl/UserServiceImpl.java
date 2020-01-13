package com.mahallem.service.Impl;

import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.User;
import com.mahallem.exception.UserNotFoundException;
import com.mahallem.repository.UserRepository;
import com.mahallem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @NotNull
    private final ModelMapper modelMapper;

    @NotNull
    final MongoTemplate mongoTemplate;

    @Override
    public UserResponse setUserDetailInformation(String userId, UserDetailRequest userDetailRequest) {

        userRepository.updateUserDetailInfo(userId, userDetailRequest);
        Optional<User> userOp = userRepository.findById(new ObjectId(userId));
        User user = userOp.orElseThrow(UserNotFoundException::new);
        return modelMapper.map(user, UserResponse.class);

    }

    @Override
    public UserResponse userInfo(String userId) {

        Optional<User> opUser = userRepository.getUserInfo(userId);
        User user = opUser.orElseThrow(UserNotFoundException::new);
        return modelMapper.map(user, UserResponse.class);

    }

    @Override
    public UserResponse getUser(String userId) {
        Optional<User> userOp = userRepository.getUserInfo(userId);
        User user = userOp.orElseThrow(UserNotFoundException::new);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public void addHouseIdToUser(String userId, ObjectId houseId) {

        userRepository.addHouseIdToUser(userId, houseId);
    }

    @Override
    public String getHouseId(String userId) {

        return getUser(userId).getHouseResponse().getId();
    }
}
