package com.mahallem.eventBusses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class EventBusImpl implements MessageListener, EventBus {

    private Queue<SubAbs> channelQueue = new ConcurrentLinkedQueue<>();

    @Autowired
    private  RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private  RedisMessageListenerContainer redisMessageListenerContainer;

    @Autowired
    private  MessageListenerAdapter addMessageListener;

    public void onMessage(Message message, byte[] pattern) {

        String channel = new String(message.getChannel());
        String messageValue= new String(message.getBody());

        channelQueue.stream()
                .filter(e -> e.channel.toString().equals(channel))
                .forEach(e->e.publish(messageValue));

    }

    @Override
    public void addSubscribe(SubAbs subAbs) {

        channelQueue.add(subAbs);
        redisMessageListenerContainer.
                addMessageListener(addMessageListener,
                        new ChannelTopic(subAbs.getChannel().toString()));
    }

    @Override
    public void post(Object s, Channel c) {

        redisTemplate.convertAndSend(c.toString(), s);
    }
}
