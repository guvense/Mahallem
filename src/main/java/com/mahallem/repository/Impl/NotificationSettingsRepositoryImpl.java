package com.mahallem.repository.Impl;

import com.mahallem.dto.Request.NotificationSettingsRequest;
import com.mahallem.entity.*;
import com.mahallem.exception.NotificationSettingUpdateException;
import com.mahallem.repository.NotificationSettingsRepository;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NotificationSettingsRepositoryImpl implements NotificationSettingsRepository {

    @NotNull
    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<NotificationSettings> getNotificationSettingById(ObjectId userId) {
        return Optional.ofNullable(mongoTemplate.findOne(Query.query(Criteria.where("user_id").is(userId)), NotificationSettings.class));
    }

    @Override
    public NotificationSettings updateNotificationSetting(ObjectId userId, NotificationSettings notificationSettings) {
        UpdateResult updateResult = mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(notificationSettings.getId())),
                new Update()
                        .set("userId", userId)
                        .set("isEmail", notificationSettings.getIsEmail())
            .set("isSms", notificationSettings.getIsSms())
            .set("isPushNotification", notificationSettings.getIsPushNotification()), NotificationSettings.class);

        if (!updateResult.wasAcknowledged()) {
            throw new NotificationSettingUpdateException();
        }

        return mongoTemplate.findOne(Query.query(Criteria.where("user_id").is(userId)), NotificationSettings.class);
    }

    @Override
    public void setNotificationSettings(ObjectId userId) {
        NotificationSettings notificationSettings = new NotificationSettings();
        notificationSettings.setUserId(userId);
        notificationSettings.setIsEmail(false);
        notificationSettings.setIsSms(false);
        notificationSettings.setIsPushNotification(false);
        mongoTemplate.insert(notificationSettings);
    }


}
