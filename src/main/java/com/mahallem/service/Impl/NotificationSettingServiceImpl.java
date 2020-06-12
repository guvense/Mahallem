package com.mahallem.service.Impl;

import com.mahallem.dto.Request.NotificationSettingRequest;
import com.mahallem.dto.Response.NotificationSettingResponse;
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
        return null;
    }

    @Override
    public NotificationSettingResponse getNotificationSetting(String id) {
        return null;
    }
}
