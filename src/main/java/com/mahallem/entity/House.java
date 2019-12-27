package com.mahallem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class House extends BaseEntity{

    private String name;

    private long grade;

    private GeoLocation geoLocation;

    private String houseStatus;

}
