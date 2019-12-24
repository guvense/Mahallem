package com.mahallem.Service.Impl;

import com.mahallem.DTO.Request.UserDetailRequest;
import com.mahallem.DTO.Response.UserResponse;
import com.mahallem.Entity.User;
import com.mahallem.Exception.UserNotFoundException;
import com.mahallem.Repository.UserRepository;
import com.mahallem.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.print.attribute.standard.Destination;
import javax.validation.constraints.NotNull;
import javax.xml.transform.Source;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @NotNull
    private final ModelMapper modelMapper;

    @NotNull final MongoTemplate mongoTemplate;

    @Override
    public UserResponse setUserDetailInformation(String userId, UserDetailRequest userDetailRequest) {

        userRepository.updateUserDetailInfo(userId, userDetailRequest);
        Optional<User> userOp = userRepository.findBy_id(new ObjectId(userId));
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
        Optional<User> userOp = userRepository.findBy_id(new ObjectId(userId));
        User user = userOp.orElseThrow(UserNotFoundException::new);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public void addHouseIdToUser(String userId, ObjectId houseId) {

        userRepository.addHouseIdToUser(userId,houseId);
    }
}
