package com.mahallem.eventbusses;

import com.mahallem.eventsender.EventValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;



public class EventBusImpl implements MessageListener, EventBus {

    private Queue<Subscriber> channelQueue = new ConcurrentLinkedQueue<>();

    private  RedisTemplate<String, String> redisTemplate;

    private  RedisMessageListenerContainer redisMessageListenerContainer;

    private  MessageListenerAdapter messageListenerAdapter;

    @Autowired
    void setRedisTemplate(RedisTemplate<String,String> redisTemplate) {
        this.redisTemplate=redisTemplate;
    }

    @Autowired
    void setRedisMessageListenerContainer(RedisMessageListenerContainer redisMessageListenerContainer) {
        this.redisMessageListenerContainer=redisMessageListenerContainer;
    }

    @Autowired
    void setMessageListenerAdapter(MessageListenerAdapter messageListenerAdapter) {
        this.messageListenerAdapter=messageListenerAdapter;
    }

    public void onMessage(Message message, byte[] pattern) {

        String channel = new String(message.getChannel());
        String messageValue = new String(message.getBody(), StandardCharsets.UTF_8);
        channelQueue.stream()
                .filter(e -> e.channel.toString().equals(channel))
                .forEach(e -> {
                    try {
                        e.publish(messageValue);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });

    }

    @Override
    public void addSubscribe(Subscriber subAbs) {

        channelQueue.add(subAbs);
        redisMessageListenerContainer.
                addMessageListener(messageListenerAdapter,
                        new ChannelTopic(subAbs.getChannel().toString()));
    }

    @Override
    public void post(EventValue s) {

        redisTemplate.convertAndSend(s.getChannel().toString(), s.toString());

    }
}
