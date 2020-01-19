package com.mahallem.eventSender;


import com.mahallem.eventBusses.Channel;
import com.mahallem.util.GsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
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
