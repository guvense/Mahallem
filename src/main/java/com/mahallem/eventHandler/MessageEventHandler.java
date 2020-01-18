package com.mahallem.eventHandler;

import com.mahallem.eventHandler.EventHandlerMessage.EventMessage;
import com.mahallem.entity.Message;
import com.mahallem.eventBusses.Channel;
import com.mahallem.eventBusses.EventBus;
import com.mahallem.eventBusses.SubAbs;
import com.mahallem.eventHandler.EventHandlerMessage.EventMessageModel;
import com.mahallem.service.MessageService;
import com.mahallem.util.GsonUtil;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
        this.channel = Channel.MESSAGE_CONTENT;
        eventBus.addSubscribe(this);

    }

    @Override
    public void publish(String json) throws IOException {

        EventMessage eventMessage = GsonUtil.convertToObject(json, EventMessage.class);

        // Mapper should be simplified
        String fromUserId = eventMessage.getContent().getFromUserId();
        String toUserId = eventMessage.getContent().getToUserId();
        Message mess = modelMapper.map(eventMessage.getContent(), Message.class);
        mess.setFromUserId(new ObjectId(fromUserId));
        mess.setToUserId(new ObjectId(toUserId));
        Message messageR = messageService.addMessage(mess);
    }
}
