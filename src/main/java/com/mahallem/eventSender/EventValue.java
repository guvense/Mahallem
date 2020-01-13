package com.mahallem.eventSender;

import com.google.gson.Gson;
import com.mahallem.eventBusses.Channel;
import com.mahallem.util.GsonUtil;
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

        return GsonUtil.toJson(this);
    }
}
