package com.mahallem.elasticsearch.service;

import com.mahallem.elasticsearch.repository.EsUserRepository;
import com.mahallem.elasticsearch.mapper.RegisteredUserMapper;
import com.mahallem.elasticsearch.model.RegisteredUser;
import com.mahallem.entity.User;
import com.mahallem.exception.UserNotFoundException;
import com.mahallem.util.AsyncUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@RequiredArgsConstructor
public class EsUserServiceImpl {

    private final EsUserRepository esUserRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(EsUserServiceImpl.class);


    private RegisteredUser saveRegisteredUser(User user) {
        RegisteredUser toElastic = RegisteredUserMapper.map.userToRegisteredUser(user);
        return esUserRepository.save(toElastic);
    }

    public void addRegisterRecord(User user) {

        AsyncUtil.invoke(() -> saveRegisteredUser(user), LOGGER::info, LOGGER::warn);

    }


}
