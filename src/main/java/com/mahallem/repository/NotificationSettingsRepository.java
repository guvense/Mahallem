package com.mahallem.repository;

import com.mahallem.entity.NotificationSettings;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface NotificationSettingsRepository {

    Optional<NotificationSettings> getNotificationSettingById(ObjectId userId);

    NotificationSettings updateNotificationSetting(ObjectId userId, NotificationSettings notificationSettings);

    void setNotificationSettings(ObjectId userId);
}
