package com.mahallem.service;

import com.mahallem.dto.Request.AuthRequest;
import com.mahallem.dto.Response.AuthResponse;
import com.mahallem.entity.User;
import com.mahallem.exception.UserOrPasswordWrongException;
import com.mahallem.exception.UsernameExistException;
import com.mahallem.repository.Impl.UserRepositoryImpl;
import com.mahallem.resource.AuthResource;
import com.mahallem.resource.UserResource;
import com.mahallem.service.Impl.AuthServiceImpl;
import com.mahallem.util.JwtUtil;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


// MethodName_StateUnderTest_ExpectedBehavior

@RestClientTest(AuthService.class)
@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @Mock
    private UserRepositoryImpl userRepository;

    @Spy
    private JwtUtil jwtUtil;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    private AuthRequest authRequest;

    private User user;


    @Before
    public void init() {
        user = UserResource.user;
        user.setId(new ObjectId("5e1a436310c40031d8a7b6d9"));
        authRequest = AuthResource.authRequest;

        when(userRepository.save(any())).thenReturn(user);
        when(bCryptPasswordEncoder.encode(any())).thenReturn("12345");
        when(userRepository.findByUserName(any())).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches(any(), any())).thenReturn(true);
    }


    @Test(expected = UsernameExistException.class)
    public void register_UserNameExist_ExceptionThrown() {
        authService.registerUser(authRequest);
    }


    @Test
    public void register_RegisterUser_CreateUserWithAllProperties() {
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.empty());
        AuthResponse authResponse = authService.registerUser(authRequest);
        assertNotNull(authResponse.getToken());
        assertNotNull(authResponse.getFirstName());
        assertNotNull(authResponse.getLastName());
        assertNotNull(authResponse.getUserName());
    }

    @Test(expected = UserOrPasswordWrongException.class)
    public void loginUser_UserNameWrong_ExceptionThrown() {
        when(userRepository.findByUserName(any())).thenReturn(Optional.empty());
        authService.loginUser(user.getUserName(), any());
    }


    @Test(expected = UserOrPasswordWrongException.class)
    public void loginUser_PasswordWrong_ExceptionThrown() {
        when(bCryptPasswordEncoder.matches(any(), any())).thenReturn(false);
        authService.loginUser(user.getUserName(), any());
    }


    @Test
    public void loginUser_LoginUser_LoginIsSuccessfully() {
        AuthResponse authResponse = authService.loginUser(user.getUserName(), any());
        assertNotNull(authResponse.getToken());
        assertNotNull(authResponse.getFirstName());
        assertNotNull(authResponse.getLastName());
        assertNotNull(authResponse.getUserName());
    }


}
