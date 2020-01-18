package com.mahallem.config;

import com.google.gson.Gson;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.Message;
import com.mahallem.entity.User;
import com.mahallem.eventHandler.EventHandlerMessage.EventMessageModel;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        // This is mapper config please write your mapping rules here
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<User, UserResponse> typeMap =
                modelMapper.createTypeMap(User.class, UserResponse.class);
        typeMap.addMapping(User::getHouse, UserResponse::setHouseResponse);

        TypeMap<EventMessageModel, Message> typeMap1 = modelMapper.createTypeMap(EventMessageModel.class, Message.class);
        //Todo: check mapper bug
        typeMap1.addMappings(mapper -> {
            mapper.map(EventMessageModel::getToUserId, Message::setToUserId);
            mapper.map(EventMessageModel::getFromUserId, Message::setFromUserId);

        });

        return modelMapper;
    }

    @Bean
    public BCryptPasswordEncoder by() {
        return new BCryptPasswordEncoder();
    }


}