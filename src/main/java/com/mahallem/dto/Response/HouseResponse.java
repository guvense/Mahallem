package com.mahallem.dto.Response;

import com.mahallem.entity.GeoLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseResponse {

    private String id;

    private String name;

    private Double grade;

    private GeoLocationResponse geoLocation;

    private String houseStatus;

    private List<HousePropertyResponse> properties;

}
