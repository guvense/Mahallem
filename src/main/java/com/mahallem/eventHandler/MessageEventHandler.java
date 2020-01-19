package com.mahallem.eventHandler;

import com.mahallem.constants.MessageState;
import com.mahallem.eventHandler.EventHandlerMessage.EventMessage;
import com.mahallem.entity.Message;
import com.mahallem.eventBusses.Channel;
import com.mahallem.eventBusses.EventBus;
import com.mahallem.eventBusses.SubAbs;
import com.mahallem.eventSender.eventSenderMessage.MessageValue;
import com.mahallem.service.MessageService;
import com.mahallem.util.GsonUtil;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.mahallem.eventModel.EventMessageModel;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MessageEventHandler extends SubAbs {

    @NotNull
    private final EventBus eventBus;

    @NotNull
    private final MessageService messageService;

    @NotNull
    private final ModelMapper modelMapper;

    @PostConstruct
    private void add() {
        this.channel = Channel.MESSAGE_CONTENT_HUB_TO_REST;
        eventBus.addSubscribe(this);

    }

    @Override
    public void publish(String json) throws IOException {

        EventMessage eventMessage = GsonUtil.convertToObject(json, EventMessage.class);

        EventMessageModel a = eventMessage.getContent();
        Message mess = modelMapper.map(a, Message.class);
        mess.setMessageState(MessageState.Receive);
        Message messageR = messageService.addMessage(mess);

        EventMessageModel map = modelMapper.map(messageR, EventMessageModel.class);

        eventBus.post(new MessageValue(null,map));
    }
}
