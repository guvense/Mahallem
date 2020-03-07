package com.mahallem.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseRequest {

    @NotNull(message = "House name cannot be null")
    private String name;

    @NotNull(message = "House grade cannot be null")
    private Double grade;

    private GeoLocationRequest geoLocation;

    @NotNull(message = "House status cannot be null")
    private String houseStatus;

    void setGeoLocation(double latitude, double longitude ) {
        this.geoLocation.setLatitude(latitude);
        this.geoLocation.setLongitude(longitude);
    }

}
