package com.mahallem.service.Impl;

import com.mahallem.dto.Request.NotificationSettingRequest;
import com.mahallem.dto.Response.NotificationSettingResponse;
import com.mahallem.entity.NotificationSettings;
import com.mahallem.exception.NotificationSettingsNotFound;
import com.mahallem.mapper.service.NotificationSettingsMapper;
import com.mahallem.repository.NotificationSettingRepository;
import com.mahallem.service.NotificationSettingService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationSettingServiceImpl implements NotificationSettingService {

    private final NotificationSettingRepository notificationSettingRepository;

    @Override
    public NotificationSettingResponse updateNotificationSetting(String userId, NotificationSettingRequest notificationSettingRequest) {
        notificationSettingRepository.updateNotificationSetting(new ObjectId(userId), notificationSettingRequest);
        return null;
    }

    @Override
    public NotificationSettingResponse getNotificationSetting(String userId) {
        NotificationSettings notificationSettings = notificationSettingRepository
                .getNotificationSettingById(new ObjectId(userId)).orElseThrow(NotificationSettingsNotFound::new);

        return NotificationSettingsMapper.map.notificationToNotificationResponse(notificationSettings);
    }
}
