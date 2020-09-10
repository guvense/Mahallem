package com.mahallem.service.impl;

import com.mahallem.dto.Request.AuthRequest;
import com.mahallem.dto.Response.AuthResponse;
import com.mahallem.elasticsearch.service.EsUserServiceImpl;
import com.mahallem.entity.User;
import com.mahallem.entity.UserValidation;
import com.mahallem.exception.EmailExistException;
import com.mahallem.exception.UserOrPasswordWrongException;
import com.mahallem.exception.UsernameExistException;
import com.mahallem.mapper.service.AuthMapper;
import com.mahallem.repository.NotificationSettingsRepository;
import com.mahallem.repository.UserRepository;
import com.mahallem.repository.UserValidationRepository;
import com.mahallem.service.AuthService;
import com.mahallem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @NotNull
    private final UserRepository userRepository;

    @NotNull
    private final UserValidationRepository userValidationRepository;

    @NotNull
    private final NotificationSettingsRepository notificationSettingsRepository;

    @NotNull
    private final EsUserServiceImpl esUserServiceImpl;

    @NotNull
    private final JwtUtil jwtUtil;

    @NotNull
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);


    @Override
    public AuthResponse registerUser(AuthRequest authRequest) {
        final User user = AuthMapper.map.authRequestToUser(authRequest);
        Optional<User> byUserName = userRepository.findByUserName(authRequest.getUsername());
        Optional<User> byEmail = userRepository.findByEmail(authRequest.getEmail());

        if (byUserName.isPresent()) {
            throw new UsernameExistException();
        }
        if (byEmail.isPresent()) {
            throw new EmailExistException();
        }
        userValidationRepository.updateEmailVerification(user.getId(), Boolean.FALSE);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        AuthResponse authResponse = AuthMapper.map.userToAuthResponse(savedUser);
        authResponse.setToken(jwtUtil.createToken(savedUser.getId()));

        notificationSettingsRepository.setNotificationSettings(savedUser.getId());
        esUserServiceImpl.addRegisterRecord(savedUser);

        return authResponse;
    }

    @Override
    public AuthResponse loginUser(String userName, String password) {
        final User user = userRepository.findByUserName(userName)
                                        .orElseThrow(UserOrPasswordWrongException::new);

        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {

            AuthResponse authResponse = AuthMapper.map.userToAuthResponse(user);
            authResponse.setToken(jwtUtil.createToken(user.getId()));
            return authResponse;
        } else {
            throw new UserOrPasswordWrongException();
        }


    }

}
