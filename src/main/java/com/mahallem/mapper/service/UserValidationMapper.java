package com.mahallem.mapper.service;

import com.mahallem.dto.Request.UserValidationRequest;
import com.mahallem.dto.Response.UserValidationResponse;
import com.mahallem.entity.UserValidation;
import com.mahallem.mapper.customize.ObjectIdMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ObjectIdMapper.class})
public interface UserValidationMapper {

    UserValidationMapper map = Mappers.getMapper(UserValidationMapper.class);

    UserValidation userValidationRequestToUserValidation(UserValidationRequest userValidationRequest);

    UserValidationResponse userValidationToUserValidationResponse(UserValidation userValidation);
}
