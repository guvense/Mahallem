package com.mahallem.elasticsearch.service;

import com.mahallem.elasticsearch.model.RegisteredUser;
import com.mahallem.elasticsearch.repository.EsUserRepository;
import com.mahallem.entity.User;
import com.mahallem.resource.ElasticSearchResource;
import com.mahallem.resource.UserResource;
import com.mahallem.util.AsyncUtil;
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

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

@RestClientTest(EsUserServiceImpl.class)
@RunWith(MockitoJUnitRunner.class)
public class EsUserServiceImplTest {

    @Mock
    private EsUserRepository esUserRepository;

    @InjectMocks
    private EsUserServiceImpl esUserService;


    @Mock
    AsyncUtil asyncUtil;

    private RegisteredUser registeredUser;
    private User user;

    @Captor
    ArgumentCaptor<Supplier<RegisteredUser>> captor;



    @Before
    public void init() {

        registeredUser = ElasticSearchResource.registeredUser;
        user = UserResource.user;
        doNothing().when(asyncUtil).invoke(any(),any(),any());

    }

    @Test
    public void q() {
        when(esUserRepository.save(any())).thenReturn(registeredUser);

        esUserService.addRegisterRecord(user);
        verify(asyncUtil).invoke(captor.capture(),any(),any());
        RegisteredUser user = captor.getValue().get();
        assertEquals(user.getAge(),registeredUser.getAge());
        assertEquals(user.getSex(),registeredUser.getSex());

    }


}