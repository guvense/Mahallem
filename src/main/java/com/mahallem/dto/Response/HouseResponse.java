package com.mahallem.dto.Response;

import com.mahallem.entity.GeoLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    private List<HousePropertyResponse> properties;

}
