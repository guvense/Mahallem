package com.mahallem.dto.Response;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationSettingResponse {

    private String id;

    private String userId;

    private Boolean isEmail;

    private Boolean isSms;

    private Boolean isPushNotification;

}
