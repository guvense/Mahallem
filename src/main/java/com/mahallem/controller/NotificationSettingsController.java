package com.mahallem.controller;

import com.mahallem.dto.Request.NotificationSettingsRequest;
import com.mahallem.dto.Response.NotificationSettingsResponse;
import com.mahallem.service.NotificationSettingsService;
import com.mahallem.util.JwtUtil;
import com.mahallem.util.ResponseUtil;
import com.mahallem.viewmodel.MainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("notification")
@RequiredArgsConstructor
public class NotificationSettingsController {

    @NotNull
    private final NotificationSettingsService notificationSettingsService;

    @GetMapping("notification-settings")
    public ResponseEntity<MainResponse<NotificationSettingsResponse>> detailNotification(HttpServletRequest httpServletRequest) {
        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return ResponseUtil.data(notificationSettingsService.getNotificationSettings(userId));
    }

    @PutMapping("update-notification-settings")
    public ResponseEntity<MainResponse<NotificationSettingsResponse>> updateNotificationSetting(
            @RequestBody NotificationSettingsRequest notificationSettingsRequest, HttpServletRequest httpServletRequest) {

        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        NotificationSettingsResponse notificationSettingsResponse = notificationSettingsService.updateNotificationSettings(userId, notificationSettingsRequest);

        return ResponseUtil.data(notificationSettingsResponse);

    }

}
