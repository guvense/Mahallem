package com.mahallem.eventHandler.EventHandlerMessage;

import com.mahallem.eventModel.EventMessageModel;
import com.mahallem.eventSender.ClientInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventMessage  {

    private ClientInfo client;

    private EventMessageModel content;

}
