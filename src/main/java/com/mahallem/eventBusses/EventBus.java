package com.mahallem.eventBusses;

public interface EventBus {

    void addSubscribe(SubAbs subAbs);
    void post(Object s,Channel c);
}
