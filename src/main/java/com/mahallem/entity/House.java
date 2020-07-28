package com.mahallem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class House extends BaseEntity{

    private String name;

    private Double grade;

    @Field("geo_location")
    private GeoLocation geoLocation;

    @Field("house_status")
    private String houseStatus;

}
