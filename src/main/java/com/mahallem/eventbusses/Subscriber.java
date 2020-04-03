package com.mahallem.eventbusses;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public abstract class Subscriber {
   public Channel channel;
   public abstract void  publish(String json) throws IOException;
}
