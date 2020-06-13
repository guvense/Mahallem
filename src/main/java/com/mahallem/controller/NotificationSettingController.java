package com.mahallem.controller;

import com.mahallem.dto.Request.NotificationSettingRequest;
import com.mahallem.dto.Response.NotificationSettingResponse;
import com.mahallem.service.NotificationSettingService;
import com.mahallem.util.JwtUtil;
import com.mahallem.util.ResponseUtil;
import com.mahallem.viewmodel.MainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("notification")
@RequiredArgsConstructor
public class NotificationSettingController {

    @NotNull
    private final NotificationSettingService notificationSettingService;

    @GetMapping("notification-settings")
    public ResponseEntity<MainResponse<NotificationSettingResponse>> detailNotification(HttpServletRequest httpServletRequest) {
        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return ResponseUtil.data(notificationSettingService.getNotificationSetting(userId));
    }

    @PutMapping("update-notification-settings")
    public ResponseEntity<MainResponse<NotificationSettingResponse>> updateNotificationSetting(
            @Valid @RequestBody NotificationSettingRequest notificationSettingRequest, HttpServletRequest httpServletRequest) {

        String userId = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        NotificationSettingResponse notificationSettingResponse = notificationSettingService.updateNotificationSetting(userId, notificationSettingRequest);

        return ResponseUtil.data(notificationSettingResponse);

    }

}
