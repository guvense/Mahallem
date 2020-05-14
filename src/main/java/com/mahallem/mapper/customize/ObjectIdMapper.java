package com.mahallem.mapper.customize;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class ObjectIdMapper {

    public String asString(ObjectId id) {

        return id == null ? null : id.toString();

    }

    public ObjectId asObjectId(String id) {
        return new ObjectId(id);
    }
}
