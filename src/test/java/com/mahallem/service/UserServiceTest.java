package com.mahallem.service;

import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.User;
import com.mahallem.exception.UserNotFoundException;
import com.mahallem.repository.UserRepository;
import com.mahallem.resource.UserResource;
import com.mahallem.service.Impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RestClientTest(UserServiceTest.class)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private String userId;

    private UserDetailRequest userDetailRequest;

    private User user;

    @Before
    public void init() {
        userDetailRequest = UserResource.userDetailRequest;
        userId = "5e1a436310c40031d8a7b6d9";
        user = UserResource.user;
        doNothing().when(userRepository).updateUserDetailInfo(any(), any());
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(userRepository.getUserInfo(any())).thenReturn(Optional.of(user));
    }

    @Test(expected = UserNotFoundException.class)
    public void setUserDetailInformation_returnEmpty_userNotFoundException() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        userService.setUserDetailInformation(userId, userDetailRequest);
    }

    @Test
    public void setUserDetailInformation_withAllProperties() {
        UserResponse userResponse = userService.setUserDetailInformation(userId, userDetailRequest);
        assertNotNull(userResponse.getUsername());
        assertNotNull(userResponse.getFirstName());
        assertNotNull(userResponse.getLastName());
    }

    @Test(expected = UserNotFoundException.class)
    public void userInfo_userNotFoundException() {
        when(userRepository.getUserInfo(any())).thenReturn(Optional.empty());
        userService.userInfo(any());
    }

    @Test
    public void userInfo_getUserWithProperties() {
        UserResponse userResponse = userService.userInfo(userId);
        assertNotNull(userResponse.getUsername());
        assertEquals(user.getUsername(), userResponse.getUsername());
        assertNotNull(userResponse.getFirstName());
        assertEquals(user.getFirstName(), userResponse.getFirstName());
        assertNotNull(userResponse.getLastName());
        assertEquals(user.getLastName(), userResponse.getLastName());

    }

    @Test(expected = UserNotFoundException.class)
    public void getUser_userNotFoundException() {
        when(userRepository.getUserInfo(any())).thenReturn(Optional.empty());
        userService.getUser(userId);
    }

    @Test
    public void getUser_getUserWithProperties() {
        UserResponse userResponse = userService.getUser(userId);
        assertNotNull(userResponse.getUsername());
        assertEquals(user.getUsername(), userResponse.getUsername());
        assertNotNull(userResponse.getFirstName());
        assertEquals(user.getFirstName(), userResponse.getFirstName());
        assertNotNull(userResponse.getLastName());
        assertEquals(user.getLastName(), userResponse.getLastName());

    }

}
