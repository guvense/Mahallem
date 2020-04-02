package com.mahallem.mapper;

import com.mahallem.dto.Request.AuthRequest;
import com.mahallem.dto.Response.AuthResponse;
import com.mahallem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ObjectIdMapper.class})
public interface AuthMapper {


   AuthMapper map = Mappers.getMapper(AuthMapper.class);

   User authRequestToUser(AuthRequest authRequest);

   AuthResponse userToAuthResponse(User user);

}
