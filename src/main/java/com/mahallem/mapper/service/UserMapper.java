package com.mahallem.mapper.service;

import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.House;
import com.mahallem.entity.User;
import com.mahallem.mapper.customize.ObjectIdMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(uses = {ObjectIdMapper.class, HouseUtilMapper.class})
public interface UserMapper {


    UserMapper map = Mappers.getMapper(UserMapper.class);

    UserResponse userToUserResponse(User user);

    User userRequestToUser(UserDetailRequest userDetailRequest);

    List<UserResponse> userListToUserResponseList(List<User> list);

}
