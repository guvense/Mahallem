package com.mahallem.dto.Request;

import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationSettingsRequest {

    @NotNull
    private Boolean isEmail;

    @NotNull
    private Boolean isSms;

    @NotNull
    private Boolean isPushNotification;

}
