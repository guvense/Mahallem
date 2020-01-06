package com.mahallem.service.Impl;

import com.mahallem.dto.Request.AuthRequest;
import com.mahallem.dto.Response.AuthResponse;
import com.mahallem.entity.User;
import com.mahallem.eventSender.ClientInfo;
import com.mahallem.eventMessage.DummyObject;
import com.mahallem.exception.UserOrPasswordWrongException;
import com.mahallem.exception.UsernameExistException;
import com.mahallem.eventBusses.EventBus;
import com.mahallem.repository.UserRepository;
import com.mahallem.service.AuthService;
import com.mahallem.util.JwtUtil;
import com.mahallem.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @NotNull
    private final UserRepository userRepository;

    @NotNull
    private final ModelMapper modelMapper;

    @NotNull
    private final JwtUtil jwtUtil;

    @NotNull
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @NotNull
    private final RedisUtil<User> stringRedisUtil;

    private final EventBus eventBus;


    @Override
    public AuthResponse registerUser(AuthRequest authRequest) {
        final User user = modelMapper.map(authRequest, User.class);

        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setAdmin(true);
        clientInfo.setId("sdladk");
        clientInfo.setUserName("Güven");
        DummyObject dummyObject = new DummyObject(clientInfo,"Hello");
        eventBus.post(dummyObject);

        Optional<User> byUserName = userRepository.findByUserName(authRequest.getUserName());

        if (byUserName.isPresent()) {
            throw new UsernameExistException();
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        AuthResponse authResponse = modelMapper.map(savedUser, AuthResponse.class);
        authResponse.setToken(jwtUtil.createToken(savedUser.get_id()));
        return authResponse;
    }

    @Override
    public AuthResponse loginUser(String userName, String password) {
        Optional<User> opUser = userRepository.findByUserName(userName);
        final User user = opUser.orElseThrow(UserOrPasswordWrongException::new);

        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            AuthResponse authResponse = modelMapper.map(user, AuthResponse.class);
            authResponse.setToken(jwtUtil.createToken(user.get_id()));
            stringRedisUtil.putValue("user"+user.getHouseId(),user);
            return authResponse;
        } else {
            throw new UserOrPasswordWrongException();
        }


    }

}
