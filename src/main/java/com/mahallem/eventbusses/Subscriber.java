package com.mahallem.eventbusses;

import java.io.IOException;

public interface Subscriber {

    void publish(String json) throws IOException;
}
