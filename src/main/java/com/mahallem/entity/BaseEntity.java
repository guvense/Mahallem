package com.mahallem.entity;

import lombok.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
class BaseEntity {

    @Id
    private ObjectId id;

    @Field("create_date")
    private Date createDate;

    @Field("update_date")
    private Date updateDate;

    BaseEntity(){

        this.createDate=new Date();
        this.updateDate=new Date();

    }

}
