package com.mahallem.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationSetting extends BaseEntity {

    @Field("user_id")
    private ObjectId userId;

    @Field("is_email")
    private Boolean isEmail;

    @Field("is_sms")
    private Boolean isSms;

    @Field("is_push_notification")
    private Boolean isPushNotification;
}
