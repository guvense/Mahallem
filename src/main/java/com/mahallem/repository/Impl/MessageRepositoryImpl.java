package com.mahallem.repository.Impl;

import com.mahallem.entity.Message;
import com.mahallem.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {

    @NotNull
    private final MongoTemplate mongoTemplate;

    @Override
    public Message save(Message message) {
        return mongoTemplate.save(message);
    }
}
