package com.mahallem.eventbusses;

import com.mahallem.eventsender.EventValue;

public interface EventBus {

    void addSubscribe(SubAbs subAbs);
    void post(EventValue eventValue);
}
