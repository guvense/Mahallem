package com.mahallem.entity;

import com.mahallem.constants.MessageState;
import com.mahallem.constants.MessageType;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message extends BaseEntity {

    @Field("from_user_id")
    private ObjectId fromUserId;

    @Field("to_user_id")
    private ObjectId toUserId;

    private String Content;

    @Field("message_type")
    private MessageType messageType;

    @Field("message_state")
    private MessageState messageState;



}
