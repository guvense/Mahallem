package com.mahallem.mapper.service;

import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.House;
import com.mahallem.entity.Permission;
import com.mahallem.entity.User;
import com.mahallem.mapper.customize.ObjectIdMapper;
import org.bson.types.ObjectId;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Mapper(uses = {ObjectIdMapper.class, HouseUtilMapper.class})
public interface UserMapper {


    UserMapper map = Mappers.getMapper(UserMapper.class);

    UserResponse userToUserResponse(User user);

    @AfterMapping
     default void setAge(User user, @MappingTarget UserResponse.UserResponseBuilder userResponse) {
        Date birthDate = user.getBirthDate();
        long diffInMillies = Math.abs(new Date().getTime() - birthDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 365L;
        userResponse.age((int) diff);
    }

    User userRequestToUser(UserDetailRequest userDetailRequest);

    List<UserResponse> userListToUserResponseList(List<User> list);

}
