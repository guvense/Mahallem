package com.mahallem.elasticsearch.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
public class BaseModel {

    private Date createdDate = new Date();
}
