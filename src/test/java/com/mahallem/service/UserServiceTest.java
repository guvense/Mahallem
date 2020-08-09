package com.mahallem.service;

import com.mahallem.blobstorage.BlobStorageService;
import com.mahallem.dto.Request.UserDetailRequest;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.Task;
import com.mahallem.entity.User;
import com.mahallem.exception.UserNotFoundException;
import com.mahallem.repository.UserRepository;
import com.mahallem.resource.UserResource;
import com.mahallem.service.impl.UserServiceImpl;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RestClientTest(UserServiceTest.class)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private BlobStorageService blobStorageService;

    @InjectMocks
    private UserServiceImpl userService;

    private String userId;

    private UserDetailRequest userDetailRequest;

    private User user;

    @Captor
    ArgumentCaptor<String> uriCaptor;

    @Captor
    ArgumentCaptor<ObjectId> userIdCaptor;

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

    @Test
    public void uploadProfilePicture_ValidParameters_Success() throws IOException {
        // Given
        String name = "file.txt";
        String originalFileName = "file.txt";
        String contentType = "text/plain";
        byte[] content = null;
        MockMultipartFile mockMultipartFile = new MockMultipartFile(name,
                originalFileName, contentType, content);
        URI uri = URI.create("http://test");

        //when
        when(blobStorageService.uploadPicture(mockMultipartFile)).thenReturn(uri);
        userService.uploadProfilePicture(mockMultipartFile,userId);
        verify(userRepository).uploadProfilePicture(uriCaptor.capture(),userIdCaptor.capture());
        String uriValue = uriCaptor.getValue();
        ObjectId userIdValue = userIdCaptor.getValue();

        //then
        assertEquals(uri.toURL().toString(),uriValue);
        assertEquals(userIdValue.toString(),userId);
    }
}
