package com.mahallem.eventHandler.EventHandlerMessage;

import com.mahallem.constants.MessageStatus;
import com.mahallem.constants.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventMessageModel {

    private String fromUserId;

    private String toUserId;

    private String content;

    private MessageType messageType;

    private MessageStatus messageStatus;
}
