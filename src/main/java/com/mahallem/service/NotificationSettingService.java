package com.mahallem.service;

import com.mahallem.dto.Request.NotificationSettingRequest;
import com.mahallem.dto.Response.NotificationSettingResponse;

import java.util.List;

public interface NotificationSettingService {

    NotificationSettingResponse updateNotificationSetting(String userId, NotificationSettingRequest notificationSettingRequest);

    NotificationSettingResponse getNotificationSetting(String id);
}
