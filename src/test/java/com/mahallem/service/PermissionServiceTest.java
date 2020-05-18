package com.mahallem.service;

import com.mahallem.constants.PermissionType;
import com.mahallem.dto.Request.PermissionAnswerRequest;
import com.mahallem.dto.Request.PermissionRequest;
import com.mahallem.dto.Response.PermissionResponse;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.Permission;
import com.mahallem.entity.User;
import com.mahallem.mapper.service.PermissionMapper;
import com.mahallem.mapper.service.UserMapper;
import com.mahallem.repository.Impl.PermissionRepositoryImpl;
import com.mahallem.resource.PermissionResource;
import com.mahallem.resource.UserResource;
import com.mahallem.service.Impl.PermissionServiceImpl;
import com.mahallem.service.Impl.UserServiceImpl;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@RestClientTest(PermissionService.class)
@RunWith(MockitoJUnitRunner.class)
public class PermissionServiceTest {
    @Mock
    private PermissionRepositoryImpl permissionRepository;


    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private PermissionServiceImpl permissionService;

    @Spy
    private PermissionMapper permissionMapper;


    private User user;

    private String userId;

    private String toUserId;

    private UserResponse userResponse;

    private PermissionRequest housePermissionRequest;

    private PermissionRequest taskPermissionRequest;

    private PermissionAnswerRequest housePermissionAnswerRequest;

    private PermissionAnswerRequest taskPermissionAnswerRequest;

    private Permission permission;

    @Before
    public void init() {
        userId = "5eb6a52256d8a1577c49621d";
        toUserId = "5eb6a49f56d8a1577c49621b";
        user = UserResource.user;
        userResponse = UserMapper.map.userToUserResponse(user);

        housePermissionRequest = PermissionResource.housePermissionRequest;
        taskPermissionRequest = PermissionResource.taskPermissionRequest;

        housePermissionAnswerRequest = PermissionResource.housePermissionAnswerRequest;
        taskPermissionAnswerRequest = PermissionResource.taskPermissionAnswerRequest;
        permission = PermissionResource.permission;
        when(userService.getUser(anyString())).thenReturn(userResponse);
        when(permissionRepository.save(any())).thenReturn(permission);
        when(permissionMapper.permissionRequestToPermission(isA(PermissionRequest.class), isA(String.class))).thenReturn(permission);
        doReturn(null).when(permissionRepository).getPermission(isA(ObjectId.class), isA(ObjectId.class), isA(Permission.class));
    }

    @Test
    public void createPermission_creatingAddUserToHousePermissionRequest_success() {
        PermissionResponse permissionResponse = permissionService.createPermission(userId, housePermissionRequest);
        assertEquals(userId, permissionResponse.getFromUserId());
        assertEquals(toUserId, permissionResponse.getToUserId());
        assertEquals(PermissionType.ADD_HOME, permissionResponse.getPermissionType());
    }

}
