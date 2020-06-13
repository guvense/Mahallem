package com.mahallem.repository;

import com.mahallem.dto.Request.NotificationSettingRequest;
import com.mahallem.entity.NotificationSettings;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface NotificationSettingRepository {


    Optional<NotificationSettings> getNotificationSettingById(ObjectId userId);

    void updateNotificationSetting(ObjectId userId, NotificationSettingRequest notificationSettingRequest);
}
