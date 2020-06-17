package com.mahallem.service;

import com.mahallem.dto.Request.NotificationSettingsRequest;
import com.mahallem.dto.Response.NotificationSettingsResponse;

public interface NotificationSettingsService {

    NotificationSettingsResponse updateNotificationSettings(String userId, NotificationSettingsRequest notificationSettingsRequest);

    NotificationSettingsResponse getNotificationSettings(String userId);

}
