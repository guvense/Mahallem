package com.mahallem.eventmodel;

import com.mahallem.constants.MessageState;
import com.mahallem.constants.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventMessageModel implements Serializable {

    private static final long serialVersionUID = 1890743140891106603L;

    private String fromUserId;

    private String toUserId;

    private String content;

    private MessageType messageType;

    private MessageState messageState;

    private String messageId;
}
