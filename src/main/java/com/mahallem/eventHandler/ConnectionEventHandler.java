package com.mahallem.eventHandler;

import com.mahallem.eventBusses.Channel;
import com.mahallem.eventBusses.EventBus;
import com.mahallem.eventBusses.SubAbs;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class ConnectionEventHandler extends SubAbs {

    private EventBus eventBus;

    ConnectionEventHandler(EventBus eventBus){
        this.eventBus=eventBus;
        this.channel= Channel.A;
    }
    @PostConstruct
    private void add(){

        eventBus.addSubscribe(this);
    }

    @Override
    public void publish(String json) {
        String a=json;

        System.out.println(a);
    }
}
