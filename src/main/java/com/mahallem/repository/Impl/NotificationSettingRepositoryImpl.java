package com.mahallem.repository.Impl;

import com.mahallem.dto.Request.NotificationSettingRequest;
import com.mahallem.entity.*;
import com.mahallem.exception.NotificationSettingUpdateException;
import com.mahallem.repository.NotificationSettingRepository;
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
public class NotificationSettingRepositoryImpl implements NotificationSettingRepository {

    @NotNull
    final MongoTemplate mongoTemplate;

    @Override
    public Optional<NotificationSetting> getNotificationSettingById(ObjectId id) {
        return Optional.ofNullable(mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), NotificationSetting.class));
    }

    @Override
    public void updateNotificationSetting(ObjectId userId, NotificationSettingRequest notificationSettingRequest) {
        UpdateResult updateResult = mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(notificationSettingRequest.getId())),
                new Update()
                        .set("userId", userId)
                        .set("isEmail", notificationSettingRequest.getIsEmail())
                        .set("isSms", notificationSettingRequest.getIsSms())
                        .set("isPushNotification", notificationSettingRequest.getIsPushNotification()), NotificationSetting.class);

        if (!updateResult.wasAcknowledged()) {
            throw new NotificationSettingUpdateException();
        }
    }
}
