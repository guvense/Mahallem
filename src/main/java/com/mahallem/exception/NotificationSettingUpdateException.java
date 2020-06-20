package com.mahallem.exception;

public class NotificationSettingUpdateException extends BaseException {

    public NotificationSettingUpdateException() {
        super(ExceptionCode.NOTIFICATION_SETTING_UPDATE_FAILED);
    }
}
