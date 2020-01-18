package com.mahallem.service.Impl;

import com.mahallem.entity.Message;
import com.mahallem.repository.MessageRepository;
import com.mahallem.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Message addMessage(Message message) {

        return messageRepository.save(message);
    }
}
