package com.mahallem.mapper.service;

import com.mahallem.dto.Request.NotificationSettingRequest;
import com.mahallem.dto.Response.NotificationSettingResponse;
import com.mahallem.entity.NotificationSettings;
import com.mahallem.mapper.customize.ObjectIdMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ObjectIdMapper.class})
public interface NotificationSettingsMapper {

    NotificationSettingsMapper map = Mappers.getMapper(NotificationSettingsMapper.class);

    NotificationSettings notificationRequestToNotification(NotificationSettingRequest notificationSettingRequest);

    NotificationSettingResponse notificationToNotificationResponse(NotificationSettings notificationSettings);


}
