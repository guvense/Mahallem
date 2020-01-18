package com.mahallem.repository;

import com.mahallem.entity.Message;

public interface MessageRepository {

    Message save(Message message);
}
