package com.mahallem.eventBusses;

import com.mahallem.eventSender.EventValue;

public interface EventBus {

    void addSubscribe(SubAbs subAbs);
    void post(EventValue eventValue);
}
