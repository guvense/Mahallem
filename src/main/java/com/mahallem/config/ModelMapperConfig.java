package com.mahallem.config;

import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.User;
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
        return modelMapper;
    }

    @Bean
    public BCryptPasswordEncoder by() {
        return new BCryptPasswordEncoder();
    }



}