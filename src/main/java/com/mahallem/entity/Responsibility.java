package com.mahallem.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Responsibility extends BaseEntity {

    private String name;

    private Date deadline;

    private String type;

    @Field("responsibility_status")
    private String responsibilityStatus;

}
