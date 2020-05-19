package com.mahallem.permission;

import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.Permission;
import com.mahallem.mapper.service.PermissionMapper;
import com.mahallem.resource.PermissionResource;
import com.mahallem.resource.UserResource;
import com.mahallem.service.UserService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class HouseInclusionPermissionOperationTest {

    @Mock
    private  UserService userService;

    @Spy
    private Permission permission = PermissionResource.permissionAddHome;

    @Mock
    private  PermissionMapper permissionMapper;

    @InjectMocks
    private HouseInclusionPermissionOperation houseInclusionPermissionOperation;

    private UserResponse userResponse;

    @Before
    public void init() {

        Permission permissionAddHome = PermissionResource.permissionAddHome;

        userResponse = UserResource.userResponse;

        when(userService.userInfo(permissionAddHome.getFromUserId().toString())).thenReturn(userResponse);
    }

    @Test
    public void approve_ValidPermission_CorrectOrderAndParameters() {

        final InOrder inOrder = inOrder(userService);

        houseInclusionPermissionOperation.approve();

        inOrder.verify(userService).userInfo(permission.getFromUserId().toString());
        inOrder.verify(userService).setApproveUserPermission(permission);
        inOrder.verify(userService).addHouseIdToUser(permission.getToUserId().toString(), new ObjectId(userResponse.getHouse().getId()));
        inOrder.verify(userService).getUser(permission.getToUserId().toString());

    }


    @Test
    public void reject_ValidPermission_CorrectOrderAndParameters() {

        final InOrder inOrder = inOrder(userService);

        houseInclusionPermissionOperation.reject();
        inOrder.verify(userService).setRejectUserPermission(permission);
        inOrder.verify(userService).getUser(permission.getToUserId().toString());

    }

    @Test
    public void save_ValidPermission_CorrectOrderAndParameters() {

        final InOrder inOrder = inOrder(permissionMapper);
        houseInclusionPermissionOperation.save();
        inOrder.verify(permissionMapper).permissionToPermissionResponse(permission);

    }

}
