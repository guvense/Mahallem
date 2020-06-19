package com.mahallem.service;

import com.mahallem.dto.Request.NotificationSettingsRequest;
import com.mahallem.dto.Response.NotificationSettingsResponse;
import com.mahallem.entity.NotificationSettings;
import com.mahallem.entity.Permission;
import com.mahallem.mapper.service.NotificationSettingsMapper;
import com.mahallem.repository.NotificationSettingsRepository;
import com.mahallem.service.Impl.NotificationSettingsServiceImpl;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.inOrder;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class NotificationSettingsServiceTest {

    @Mock
    private NotificationSettingsRepository notificationSettingsRepository;

    @Mock
    private NotificationSettingsMapper notificationSettingsMapper;

    @InjectMocks
    private NotificationSettingsServiceImpl notificationSettingsService;

    @Before
    public void init() {

    }

    @Test
    public void updateNotificationSettings_validParameters_SuccessReturnNotificationResponse(){
        //given
        final NotificationSettingsRequest notificationSettingsRequest = NotificationSettingsRequest.builder()
                .isPushNotification(true)
                .isEmail(true)
                .isSms(false)
                .build();

        final NotificationSettings notificationSettings = NotificationSettings.builder()
                .isPushNotification(true)
                .isEmail(true)
                .isSms(false)
                .build();

        final NotificationSettingsResponse notificationSettingsResponse = NotificationSettingsResponse.builder()
                .isPushNotification(true)
                .isEmail(true)
                .isSms(false)
                .build();

        when(notificationSettingsMapper.notificationRequestToNotification(notificationSettingsRequest)).thenReturn(notificationSettings);
        when(notificationSettingsRepository.updateNotificationSetting(any(),eq(notificationSettings))).thenReturn(notificationSettings);
        when(notificationSettingsMapper.notificationToNotificationResponse(notificationSettings)).thenReturn(notificationSettingsResponse);

        // when
        NotificationSettingsResponse response = notificationSettingsService.updateNotificationSettings("5e1a436310c40031d8a7b6d9", notificationSettingsRequest);

        // then
        assertTrue(response.getIsEmail());
        assertTrue(response.getIsPushNotification());
        assertFalse(response.getIsSms());

        InOrder inOrder = inOrder(notificationSettingsMapper,
                notificationSettingsRepository,
                notificationSettingsMapper);
        inOrder.verify(notificationSettingsMapper).notificationRequestToNotification(notificationSettingsRequest);
        inOrder.verify(notificationSettingsRepository).updateNotificationSetting(any() ,eq(notificationSettings));
        inOrder.verify(notificationSettingsMapper).notificationToNotificationResponse(notificationSettings);
    }

    @Test
    public void getNotificationSettings_validParameters_SuccessReturnNotificationResponse(){
        // given

        final NotificationSettings notificationSettings = NotificationSettings.builder()
                .isPushNotification(true)
                .isEmail(true)
                .isSms(false)
                .build();

        final NotificationSettingsResponse notificationSettingsResponse = NotificationSettingsResponse.builder()
                .isPushNotification(true)
                .isEmail(true)
                .isSms(false)
                .build();

        // when
        when(notificationSettingsRepository.getNotificationSettingById(any())).thenReturn(Optional.of(notificationSettings));
        when(notificationSettingsMapper.notificationToNotificationResponse(notificationSettings)).thenReturn(notificationSettingsResponse);

        NotificationSettingsResponse response = notificationSettingsService.getNotificationSettings("5e1a436310c40031d8a7b6d9");

        // then

        assertTrue(response.getIsEmail());
        assertTrue(response.getIsPushNotification());
        assertFalse(response.getIsSms());

        InOrder inOrder = inOrder(notificationSettingsRepository,
                notificationSettingsMapper);

        inOrder.verify(notificationSettingsRepository).getNotificationSettingById(any());
        inOrder.verify(notificationSettingsMapper).notificationToNotificationResponse(eq(notificationSettings));

    }



}