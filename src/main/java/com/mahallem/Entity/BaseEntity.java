package com.mahallem.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
class BaseEntity {

    @Id
    private ObjectId _id;

    private Date createDate;

    private Date updateDate;

    BaseEntity(){

        this.createDate=new Date();
        this.updateDate=new Date();

    }

}
