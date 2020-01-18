package com.mahallem.eventSender.eventSenderMessage;


import com.mahallem.eventBusses.Channel;
import com.mahallem.eventSender.ClientInfo;
import com.mahallem.eventSender.EventValue;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class DummyObject extends EventValue implements Serializable {

    private static final long serialVersionUID = -3586405944337487096L;

    @Getter
    @Setter
    private String message;

    public DummyObject(ClientInfo clientInfo, String message) {
        super(clientInfo);
        this.clientInfo=clientInfo;
        this.message=message;
        this.setChannel(Channel.A);

    }

}
