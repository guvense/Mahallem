package com.mahallem.mapper.service;

import com.mahallem.dto.Request.NotificationSettingsRequest;
import com.mahallem.dto.Response.NotificationSettingsResponse;
import com.mahallem.entity.NotificationSettings;

import org.mapstruct.factory.Mappers;

public interface NotificationSettingsMapper {

    NotificationSettingsMapper map = Mappers.getMapper(NotificationSettingsMapper.class);

    NotificationSettings notificationRequestToNotification(NotificationSettingsRequest notificationSettingsRequest);

    NotificationSettingsResponse notificationToNotificationResponse(NotificationSettings notificationSettings);

}
