package com.mahallem.Service.Impl;

import com.mahallem.DTO.Request.AuthRequest;
import com.mahallem.DTO.Response.UserResponse;
import com.mahallem.Entity.User;
import com.mahallem.Repository.AuthRepository;
import com.mahallem.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @NotNull
    private final AuthRepository authRepository;
    
    @NotNull
    private final ModelMapper modelMapper;

    @NotNull
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserResponse registerUser(AuthRequest authRequest) {
        final User user = modelMapper.map(authRequest, User.class);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User savedUser = authRepository.save(user);

        return modelMapper.map(savedUser, UserResponse.class);
    }
}
