package com.mahallem.eventhandler;

import com.mahallem.constants.MessageState;
import com.mahallem.eventhandler.eventhandlermessage.EventMessage;
import com.mahallem.entity.Message;
import com.mahallem.eventbusses.Channel;
import com.mahallem.eventbusses.EventBus;
import com.mahallem.eventbusses.SubAbs;
import com.mahallem.eventsender.eventsendermessage.MessageValue;
import com.mahallem.service.MessageService;
import com.mahallem.util.GsonUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.mahallem.eventmodel.EventMessageModel;

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
        mess.setMessageState(MessageState.RECEIVE);
        Message messageR = messageService.addMessage(mess);

        EventMessageModel map = modelMapper.map(messageR, EventMessageModel.class);

        eventBus.post(new MessageValue(null,map));
    }
}
