package com.mahallem.mapper;

import com.mahallem.dto.Request.AuthRequest;
import com.mahallem.dto.Response.AuthResponse;
import com.mahallem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class AuthMapper {


   public static final AuthMapper map = Mappers.getMapper(AuthMapper.class);

    public abstract User authRequestToUser(AuthRequest authRequest);

   public  AuthResponse userToAuthResponse(User user) {
       return AuthResponse.builder()
               .firstName(user.getFirstName())
               .lastName(user.getLastName())
               .id(user.getId().toString())
               .userName(user.getUserName())
               .build();
   }

}
