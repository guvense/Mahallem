package com.mahallem.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Activity extends BaseEntity{

    private String name;

    @Field("start_date")
    private Date startDate;

    @Field("end_date")
    private Date endDate;

    private String type;

    @Field("organization_type")
    private String organizationType;

    @Field("activity_status")
    private String activityStatus;

    @Field("participant_list")
    private String participantList;
}
