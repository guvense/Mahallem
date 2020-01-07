package com.mahallem.eventBusses;

import java.io.IOException;

public interface Subscriber {

    void publish(String json) throws IOException;
}
