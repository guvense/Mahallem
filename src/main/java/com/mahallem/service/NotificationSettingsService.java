package com.mahallem.service;

import com.mahallem.dto.Request.NotificationSettingsRequest;
import com.mahallem.dto.Response.NotificationSettingsResponse;
import org.bson.types.ObjectId;

public interface NotificationSettingsService {

    NotificationSettingsResponse updateNotificationSettings(String userId, NotificationSettingsRequest notificationSettingsRequest);

    NotificationSettingsResponse getNotificationSettings(ObjectId userId);

}
