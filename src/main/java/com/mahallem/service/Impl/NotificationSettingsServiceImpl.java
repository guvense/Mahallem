package com.mahallem.service.Impl;

import com.mahallem.dto.Request.NotificationSettingsRequest;
import com.mahallem.dto.Response.NotificationSettingsResponse;
import com.mahallem.entity.NotificationSettings;
import com.mahallem.exception.NotificationSettingsNotFound;
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

    private final NotificationSettingsMapper notificationSettingsMapper;

    @Override
    public NotificationSettingsResponse updateNotificationSettings(String userId, NotificationSettingsRequest notificationSettingsRequest) {
        final NotificationSettings notificationSettings = notificationSettingsMapper.notificationRequestToNotification(notificationSettingsRequest);
        NotificationSettings updatedSettings = notificationSettingsRepository.updateNotificationSetting(new ObjectId(userId), notificationSettings);
        return notificationSettingsMapper.notificationToNotificationResponse(updatedSettings);
    }

    @Override
    public NotificationSettingsResponse getNotificationSettings(String userId) {
        NotificationSettings notificationSettings = notificationSettingsRepository.getNotificationSettingById(new ObjectId(userId))
                                                                                  .orElseThrow(NotificationSettingsNotFound::new);
        return notificationSettingsMapper.notificationToNotificationResponse(notificationSettings);
    }

}
