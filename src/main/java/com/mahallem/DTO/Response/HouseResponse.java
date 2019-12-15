package com.mahallem.DTO.Response;

import com.mahallem.Entity.GeoLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseResponse {

    private String _id;

    private String name;

    private long grade;

    private GeoLocation geoLocation;

    private String houseStatus;

}
