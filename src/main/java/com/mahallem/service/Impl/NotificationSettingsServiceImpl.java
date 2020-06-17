package com.mahallem.service.Impl;

import com.mahallem.dto.Request.NotificationSettingsRequest;
import com.mahallem.dto.Response.NotificationSettingsResponse;
import com.mahallem.entity.House;
import com.mahallem.entity.NotificationSettings;
import com.mahallem.exception.NotificationSettingsNotFound;
import com.mahallem.mapper.service.HouseMapper;
import com.mahallem.mapper.service.NotificationSettingsMapper;
import com.mahallem.repository.NotificationSettingsRepository;
import com.mahallem.service.NotificationSettingsService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationSettingsServiceImpl implements NotificationSettingsService {

    private final NotificationSettingsRepository notificationSettingsRepository;

    @Override
    public NotificationSettingsResponse updateNotificationSettings(String userId, NotificationSettingsRequest notificationSettingsRequest) {
        final NotificationSettings notificationSettings = NotificationSettingsMapper.map.notificationRequestToNotification(notificationSettingsRequest);
        NotificationSettings updatedSettings = notificationSettingsRepository.updateNotificationSetting(new ObjectId(userId), notificationSettings);
        return NotificationSettingsMapper.map.notificationToNotificationResponse(updatedSettings);
    }

    @Override
    public NotificationSettingsResponse getNotificationSettings(String userId) {
        NotificationSettings notificationSettings = notificationSettingsRepository.getNotificationSettingById(new ObjectId(userId)).orElseThrow(NotificationSettingsNotFound::new);

        return NotificationSettingsMapper.map.notificationToNotificationResponse(notificationSettings);
    }

}
