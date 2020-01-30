package com.mahallem.service;

import com.mahallem.dto.Request.AuthRequest;
import com.mahallem.dto.Response.AuthResponse;
import com.mahallem.entity.User;
import com.mahallem.exception.UsernameExistException;
import com.mahallem.repository.Impl.UserRepositoryImpl;
import com.mahallem.resource.AuthResource;
import com.mahallem.service.Impl.AuthServiceImpl;
import com.mahallem.util.JwtUtil;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;


// MethodName_StateUnderTest_ExpectedBehavior

@RestClientTest(AuthService.class)
@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @Mock
    private UserRepositoryImpl userRepository;

    @Spy
    private ModelMapper modelMapper;

    @Spy
    private JwtUtil jwtUtil;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    private AuthRequest authRequest;

    private User u;

    @Before
    public void init() {

        u = new User();
        u.setUserName("test");

        this.authRequest = AuthResource.authRequest;

        when(userRepository.save(any())).thenReturn(u);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("12345");

    }


    @Test(expected = UsernameExistException.class)
    public void register_UserNameExist_ExceptionThrown() {

        when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(u));
        AuthResponse authResponse = authService.registerUser(authRequest);
        assertNotNull(authResponse.getToken());

    }

    @Test
    public void register_RegisterUser_CreateUserWithAllProperties() {

        when(userRepository.save(any())).thenAnswer((Answer) invocationOnMock -> {
            User user = invocationOnMock.getArgument(0);
            user.setId(new ObjectId("5e1a436310c40031d8a7b6d9"));
            return user;

        });


        when(userRepository.findByUserName(anyString())).thenReturn(Optional.empty());
        AuthResponse authResponse = authService.registerUser(authRequest);
        assertNotNull(authResponse.getToken());
        assertNotNull(authResponse.getFirstName());
        assertNotNull(authResponse.getLastName());
        assertNotNull(authResponse.getUserName());


    }
}
