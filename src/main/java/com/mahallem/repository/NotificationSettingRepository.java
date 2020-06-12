package com.mahallem.repository;

import com.mahallem.dto.Request.NotificationSettingRequest;
import com.mahallem.entity.NotificationSetting;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface NotificationSettingRepository {


    Optional<NotificationSetting> getNotificationSettingById(ObjectId id);

    void updateNotificationSetting(ObjectId userId, NotificationSettingRequest notificationSettingRequest);
}
