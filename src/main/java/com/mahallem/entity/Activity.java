package com.mahallem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activity extends BaseEntity{

    private String name;

    private Date startDate;

    private Date endDate;

    private String type;

    private String organizationType;

    private String activityStatus;

    private String participantList;
}
