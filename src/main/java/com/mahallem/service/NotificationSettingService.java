package com.mahallem.service;

import com.mahallem.dto.Request.NotificationSettingRequest;
import com.mahallem.dto.Response.NotificationSettingResponse;

public interface NotificationSettingService {

    NotificationSettingResponse updateNotificationSetting(String userId, NotificationSettingRequest notificationSettingRequest);

    NotificationSettingResponse getNotificationSetting(String userId);
}
