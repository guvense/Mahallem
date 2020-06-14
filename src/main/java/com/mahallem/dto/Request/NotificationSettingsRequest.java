package com.mahallem.dto.Request;

import lombok.*;
import org.bson.types.ObjectId;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationSettingsRequest {

    private Boolean isEmail;

    private Boolean isSms;

    private Boolean isPushNotification;

}
