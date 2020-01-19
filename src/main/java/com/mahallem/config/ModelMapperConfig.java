package com.mahallem.config;

import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.Message;
import com.mahallem.entity.User;
import com.mahallem.eventModel.EventMessageModel;
import org.bson.types.ObjectId;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        Converter<String, ObjectId> toObjectId = new AbstractConverter<String, ObjectId>() {
            protected ObjectId convert(String source) {
                return source == null ? null : new ObjectId(source);
            }
        };

        // This is mapper config please write your mapping rules here
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<User, UserResponse> typeMap =
                modelMapper.createTypeMap(User.class, UserResponse.class);
        typeMap.addMapping(User::getHouse, UserResponse::setHouseResponse);

        TypeMap<EventMessageModel, Message> typeMap1 = modelMapper.createTypeMap(EventMessageModel.class, Message.class);
        typeMap1.addMappings(mapper -> {
             mapper.using(toObjectId)
            .map(EventMessageModel::getToUserId, Message::setToUserId);

             mapper.using(toObjectId)
            .map(EventMessageModel::getFromUserId, Message::setFromUserId);

        });

        return modelMapper;
    }

    @Bean
    public BCryptPasswordEncoder by() {
        return new BCryptPasswordEncoder();
    }


}