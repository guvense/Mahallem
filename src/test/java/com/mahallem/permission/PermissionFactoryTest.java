package com.mahallem.permission;

import com.mahallem.entity.Permission;
import com.mahallem.mapper.service.PermissionMapper;
import com.mahallem.resource.PermissionResource;
import com.mahallem.service.TaskService;
import com.mahallem.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;



@RestClientTest(PermissionFactory.class)
@RunWith(MockitoJUnitRunner.class)
public class PermissionFactoryTest {

    @Mock
    private UserService userService;

    @Mock
    private TaskService taskService;

    @Mock
    private PermissionMapper permissionMapper;

    @InjectMocks
    private PermissionFactory permissionFactory;

    @Test
    public void getPermission_AddHomePermissionType_ReturnHouseInclusionPermissionOperation() {
        Permission permissionAddHome = PermissionResource.permissionAddHome;

        PermissionOperation permission = permissionFactory.getPermission(permissionAddHome);

        assertThat(permission,instanceOf(HouseInclusionPermissionOperation.class));

    }

    @Test
    public void getPermission_TaskAssignPermissionType_TaskAssignPermissionOperation() {
        Permission permissionAddHome = PermissionResource.permissionTaskAssign;

        PermissionOperation permission = permissionFactory.getPermission(permissionAddHome);

        assertThat(permission,instanceOf(TaskAssignPermissionOperation.class));

    }


}
