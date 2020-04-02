package com.mahallem.mapper;

import org.bson.types.ObjectId;

public class ObjectIdMapper {

    public String asString(ObjectId id) {
        return id.toString();
    }

    public ObjectId asObjectId(String id) {
        return new ObjectId(id);
    }
}
