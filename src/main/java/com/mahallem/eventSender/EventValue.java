package com.mahallem.eventSender;

import com.google.gson.Gson;
import com.mahallem.eventBusses.Channel;
import lombok.Getter;
import lombok.Setter;

public abstract class EventValue  {

    @Setter
    @Getter
    private Channel channel;

    protected ClientInfo clientInfo;

    public EventValue(ClientInfo clientInfo) {
        this.clientInfo=clientInfo;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
