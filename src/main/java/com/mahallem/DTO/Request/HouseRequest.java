package com.mahallem.DTO.Request;

import com.mahallem.Entity.GeoLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseRequest {

    @NotNull(message = "House name cannot be null")
    private String name;

    @NotNull(message = "House grade cannot be null")
    private long grade;

    private GeoLocation geoLocation;

    @NotNull(message = "House status cannot be null")
    private String houseStatus;

}
