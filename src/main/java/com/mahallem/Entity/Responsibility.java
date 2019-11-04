package com.mahallem.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Responsibility extends BaseEntity {

    private String name;
    private Date deadline;
    private String type;
    private String responsibilityStatus;

}
