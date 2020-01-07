package com.mahallem.eventHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahallem.eventBusses.Channel;
import com.mahallem.eventBusses.EventBus;
import com.mahallem.eventBusses.SubAbs;
import com.mahallem.eventMessage.DummyObject;
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
        DummyObject dummyObject = objectMapper.readValue(json, DummyObject.class);

    }
}
