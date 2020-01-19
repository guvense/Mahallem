package com.mahallem.eventhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahallem.eventbusses.Channel;
import com.mahallem.eventbusses.EventBus;
import com.mahallem.eventbusses.SubAbs;
import com.mahallem.eventsender.eventsendermessage.DummyObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class ConnectionEventHandler extends SubAbs {

    private final ObjectMapper objectMapper;

    private final EventBus eventBus;

    @PostConstruct
    private void add() {
        this.channel = Channel.A;
        eventBus.addSubscribe(this);
    }

    @Override
    public void publish(String json) throws IOException {
//        DummyObject dummyObject = objectMapper.readValue(json, DummyObject.class);

    }
}
