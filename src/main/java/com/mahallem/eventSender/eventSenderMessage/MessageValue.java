package com.mahallem.eventSender.eventSenderMessage;

import com.mahallem.eventBusses.Channel;
import com.mahallem.eventModel.EventMessageModel;
import com.mahallem.eventSender.ClientInfo;
import com.mahallem.eventSender.EventValue;
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
