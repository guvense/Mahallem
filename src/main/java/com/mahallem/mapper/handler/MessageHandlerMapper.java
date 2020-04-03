package com.mahallem.mapper.handler;

import com.mahallem.entity.Message;
import com.mahallem.eventhandler.eventhandlermessage.EventMessage;
import com.mahallem.eventmodel.EventMessageModel;
import com.mahallem.mapper.customize.ObjectIdMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ObjectIdMapper.class})
public interface MessageHandlerMapper {

    MessageHandlerMapper map = Mappers.getMapper(MessageHandlerMapper.class);

    Message eventMessageModelToMessage(EventMessageModel eventMessageModel);

    EventMessageModel messageToEventMessageModel(Message message);
}
