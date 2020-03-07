package com.mahallem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class House extends BaseEntity{

    private String name;

    private Double grade;

    private GeoLocation geoLocation;

    private String houseStatus;

}
