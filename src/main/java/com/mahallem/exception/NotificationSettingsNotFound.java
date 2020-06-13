package com.mahallem.exception;

public class NotificationSettingsNotFound extends BaseException{

    public NotificationSettingsNotFound() {
        super(ExceptionCode.NOTIFICATION_NOT_FOUND);
    }
}
