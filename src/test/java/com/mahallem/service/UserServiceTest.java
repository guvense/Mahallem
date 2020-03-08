package com.mahallem.service;

import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.User;
import com.mahallem.exception.UserNotFoundException;
import com.mahallem.repository.UserRepository;
import com.mahallem.resource.UserResource;
import com.mahallem.service.Impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RestClientTest(UserServiceTest.class)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private String userId;

    private UserDetailRequest userDetailRequest;

    private User user;

    private UserResponse userResponse;

    @Before
    public void init() {
        userResponse = UserResource.userResponse;
        userDetailRequest = UserResource.userDetailRequest;
        userId = "5e1a436310c40031d8a7b6d9";
        user = UserResource.user;
        doNothing().when(userRepository).updateUserDetailInfo(any(), any());
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(userRepository.getUserInfo(any())).thenReturn(Optional.of(user));
    }

    @Test(expected = UserNotFoundException.class)
    public void setUserDetailInformation_userNotFoundException() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        userService.setUserDetailInformation(userId, userDetailRequest);
    }

    @Test
    public void setUserDetailInformation_withAllProperties() {
        UserResponse userResponse = userService.setUserDetailInformation(userId, userDetailRequest);
        Assert.assertNotNull(userResponse.getUserName());
        Assert.assertNotNull(userResponse.getFirstName());
        Assert.assertNotNull(userResponse.getLastName());
    }

    @Test(expected = UserNotFoundException.class)
    public void userInfo_userNotFoundException() {
        when(userRepository.getUserInfo(any())).thenReturn(Optional.empty());
        userService.userInfo(userId);
    }

    @Test
    public void userInfo_getUserWithProperties() {
        UserResponse userResponse = userService.userInfo(userId);
        Assert.assertNotNull(userResponse.getUserName());
        Assert.assertEquals(user.getUserName(), userResponse.getUserName());
        Assert.assertNotNull(userResponse.getFirstName());
        Assert.assertEquals(user.getFirstName(), userResponse.getFirstName());
        Assert.assertNotNull(userResponse.getLastName());
        Assert.assertEquals(user.getLastName(), userResponse.getLastName());

    }

    @Test(expected = UserNotFoundException.class)
    public void getUser_userNotFoundException() {
        when(userRepository.getUserInfo(any())).thenReturn(Optional.empty());
        userService.getUser(userId);
    }

    @Test
    public void getUser_getUserWithProperties() {
        UserResponse userResponse = userService.getUser(userId);
        Assert.assertNotNull(userResponse.getUserName());
        Assert.assertEquals(user.getUserName(), userResponse.getUserName());
        Assert.assertNotNull(userResponse.getFirstName());
        Assert.assertEquals(user.getFirstName(), userResponse.getFirstName());
        Assert.assertNotNull(userResponse.getLastName());
        Assert.assertEquals(user.getLastName(), userResponse.getLastName());

    }

    @Test
    public void getHouseId_getUserHouseProperties() {
        when(userService.getUser(any())).thenReturn(userResponse);
        Assert.assertNotNull(userService.getHouseId(userId));
        Assert.assertEquals(userResponse.getHouseResponse().getId(), userService.getHouseId(userId));
    }

}
