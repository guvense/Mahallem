package com.mahallem.entity;

import com.mahallem.constants.MessageStatus;
import com.mahallem.constants.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message extends BaseEntity {

    private ObjectId fromUserId;

    private ObjectId toUserId;

    private String Content;

    private MessageType messageType;

    private MessageStatus messageStatus = MessageStatus.Receive;



}
