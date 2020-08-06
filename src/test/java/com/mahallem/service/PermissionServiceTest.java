package com.mahallem.service;


import com.mahallem.dto.Request.PermissionAnswerRequest;
import com.mahallem.dto.Request.PermissionRequest;
import com.mahallem.dto.Response.PermissionResponse;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.Permission;
import com.mahallem.exception.PermissionRequestExistException;
import com.mahallem.mapper.customize.ObjectIdMapper;
import com.mahallem.mapper.service.PermissionMapper;
import com.mahallem.permission.PermissionFactory;
import com.mahallem.permission.PermissionOperation;
import com.mahallem.repository.PermissionRepository;
import com.mahallem.resource.PermissionResource;
import com.mahallem.resource.UserResource;
import com.mahallem.service.impl.PermissionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RestClientTest(PermissionService.class)
@RunWith(MockitoJUnitRunner.class)
public class PermissionServiceTest {

    @Mock
    private  PermissionRepository permissionRepository;

    @Mock
    private  UserService userService;

    @Mock
    private  PermissionMapper permissionMapper;

    @Mock
    private  PermissionFactory permissionFactory;

    @Spy
    private ObjectIdMapper objectIdMapper;

    @InjectMocks
    private PermissionServiceImpl permissionService;

    private List<Permission> permissions;

    private String userId;


    private Permission permission;

    @Before
    public void init() {
        userId = "5eb8054656d8a15448079ba0";
        permission = PermissionResource.permissionAddHome;
        permissions = Collections.singletonList(permission);
    }

    @Test
    public void getAllPendingPermissionRequest_ValidUserId_ReturnPermissionResponse(){
        when(permissionRepository.getAllPendingPermissions(userId,Pageable.unpaged())).thenReturn(permissions);
        when(permissionMapper.permissionToPermissionResponse(permissions)).thenReturn(Collections.singletonList(PermissionResource.permissionResponse));

        Page<PermissionResponse> permissionResponses = permissionService.getAllPendingPermissionRequest(userId, Pageable.unpaged() );

        PermissionResponse permissionResponse = permissionResponses.getContent().get(0);

        assertEquals(permission.getFromUserId().toString(), permissionResponse.getFromUserId());
        assertEquals(permission.getToUserId().toString(), permissionResponse.getToUserId());
        assertEquals(permission.getPermissionType(), permissionResponse.getPermissionType());

    }

    @Test
    public void approvePermissionRequest_AddHomePermissionRequestAsParameter_VerifyMethodOrder(){

        PermissionAnswerRequest request = PermissionResource.housePermissionAnswerRequest;
        PermissionOperation permissionOperation = mock(PermissionOperation.class);
        when(permissionFactory.getPermission(any())).thenReturn(permissionOperation);
        permissionService.approvePermissionRequest(userId,request);

        InOrder inOrder = inOrder(permissionFactory, permissionOperation);
        inOrder.verify(permissionFactory).getPermission(any());
        inOrder.verify(permissionOperation).approve();

    }

    @Test
    public void rejectPermissionRequest_AddHomePermissionRequestAsParameter_VerifyMethodOrder(){

        PermissionAnswerRequest request = PermissionResource.housePermissionAnswerRequest;
        PermissionOperation permissionOperation = mock(PermissionOperation.class);
        when(permissionFactory.getPermission(any())).thenReturn(permissionOperation);
        permissionService.rejectPermissionRequest(userId,request);

        InOrder inOrder = inOrder(permissionFactory, permissionOperation);
        inOrder.verify(permissionFactory).getPermission(any());
        inOrder.verify(permissionOperation).reject();

    }


    @Test(expected = PermissionRequestExistException.class)
    public void createPermission_PermissionNotExist_ThrownException(){

        PermissionRequest housePermissionRequest = PermissionResource.housePermissionRequest;
        when(permissionRepository.getPermission(any(),any(), eq(permission))).thenReturn(Optional.of(permission));
        when(permissionMapper.permissionRequestToPermission(housePermissionRequest,userId)).thenReturn(permission);
        UserResponse userResponse = UserResource.userResponse;
        when(userService.getUser(any())).thenReturn(userResponse);
        permissionService.createPermission(userId,housePermissionRequest);
    }

    @Test
    public void createPermission_ValidParameters_CorrectInorder(){

        PermissionRequest housePermissionRequest = PermissionResource.housePermissionRequest;
        UserResponse userResponse = UserResource.userResponse;
        PermissionOperation permissionOperation = mock(PermissionOperation.class);


        when(permissionRepository.getPermission(any(),any(), eq(permission))).thenReturn(Optional.empty());
        when(permissionMapper.permissionRequestToPermission(housePermissionRequest,userId)).thenReturn(permission);
        when(permissionRepository.save(permission)).thenReturn(permission);
        when(permissionFactory.getPermission(any())).thenReturn(permissionOperation);

        when(userService.getUser(any())).thenReturn(userResponse);
        permissionService.createPermission(userId,housePermissionRequest);

        InOrder inOrder = inOrder(permissionRepository,permissionFactory, permissionOperation);

        inOrder.verify(permissionRepository).save(permission);
        inOrder.verify(permissionFactory).getPermission(permission);
        inOrder.verify(permissionOperation).save();
    }


}
