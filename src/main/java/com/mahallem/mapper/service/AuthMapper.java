package com.mahallem.mapper.service;

import com.mahallem.constants.Status;
import com.mahallem.dto.Request.AuthRequest;
import com.mahallem.dto.Response.AuthResponse;
import com.mahallem.entity.User;
import com.mahallem.mapper.customize.ObjectIdMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ObjectIdMapper.class})
public interface AuthMapper {

   AuthMapper map = Mappers.getMapper(AuthMapper.class);

   User authRequestToUser(AuthRequest authRequest);

   AuthResponse userToAuthResponse(User user);

   @AfterMapping
   default void initializeStatus(AuthRequest authRequest,  @MappingTarget User.UserBuilder user) {
      user.status(Status.ACTIVE);
   }


}
