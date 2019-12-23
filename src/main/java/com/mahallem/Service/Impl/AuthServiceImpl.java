package com.mahallem.Service.Impl;

import com.mahallem.DTO.Request.AuthRequest;
import com.mahallem.DTO.Response.AuthResponse;
import com.mahallem.Entity.User;
import com.mahallem.Exception.UserOrPasswordWrongException;
import com.mahallem.Exception.UsernameExistException;
import com.mahallem.Repository.UserRepository;
import com.mahallem.Service.AuthService;
import com.mahallem.Util.JwtUtil;
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


    @Override
    public AuthResponse registerUser(AuthRequest authRequest) {
        final User user = modelMapper.map(authRequest, User.class);

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
            return authResponse;
        } else {
            throw new UserOrPasswordWrongException();
        }


    }

}
