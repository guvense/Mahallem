package com.mahallem.eventhandler;

import com.mahallem.constants.MessageState;
import com.mahallem.eventbusses.Subscriber;
import com.mahallem.eventhandler.eventhandlermessage.EventMessage;
import com.mahallem.entity.Message;
import com.mahallem.eventbusses.Channel;
import com.mahallem.eventbusses.EventBus;
import com.mahallem.eventsender.eventsendermessage.MessageValue;
import com.mahallem.mapper.handler.MessageHandlerMapper;
import com.mahallem.service.MessageService;
import com.mahallem.util.GsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.mahallem.eventmodel.EventMessageModel;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MessageEventHandler extends Subscriber {

    @NotNull
    private final EventBus eventBus;

    @NotNull
    private final MessageService messageService;

    @PostConstruct
    private void add() {
        this.channel = Channel.MESSAGE_CONTENT_HUB_TO_REST;
        eventBus.addSubscribe(this);

    }

    @Override
    public void publish(String json) throws IOException {

        EventMessage eventMessage = GsonUtil.convertToObject(json, EventMessage.class);

        EventMessageModel eventMessageModel = eventMessage.getContent();
        Message mappedMessage = MessageHandlerMapper.map.eventMessageModelToMessage(eventMessageModel);
        mappedMessage.setMessageState(MessageState.RECEIVE);
        Message message = messageService.addMessage(mappedMessage);

        EventMessageModel map = MessageHandlerMapper.map.messageToEventMessageModel(message);

        eventBus.post(new MessageValue(null,map));
    }
}
