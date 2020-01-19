package com.mahallem.eventsender.eventsendermessage;

import com.mahallem.eventbusses.Channel;
import com.mahallem.eventmodel.EventMessageModel;
import com.mahallem.eventsender.ClientInfo;
import com.mahallem.eventsender.EventValue;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class MessageValue extends EventValue implements Serializable {

    private static final long serialVersionUID = -7466865423759931907L;

    @Getter
    @Setter
    private EventMessageModel eventMessageModel;

    public MessageValue(ClientInfo clientInfo, EventMessageModel eventMessageModel) {
        super(clientInfo);
        this.setChannel(Channel.MESSAGE_CONTENT_REST_TO_HUB);
        this.eventMessageModel = eventMessageModel;
    }
}
