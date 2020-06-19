package com.mahallem.mapper.service;

import com.mahallem.dto.Request.NotificationSettingsRequest;
import com.mahallem.dto.Response.NotificationSettingsResponse;
import com.mahallem.entity.NotificationSettings;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificationSettingsMapper {

    NotificationSettings notificationRequestToNotification(NotificationSettingsRequest notificationSettingsRequest);

    NotificationSettingsResponse notificationToNotificationResponse(NotificationSettings notificationSettings);
}
