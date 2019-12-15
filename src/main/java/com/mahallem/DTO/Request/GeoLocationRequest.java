package com.mahallem.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class GeoLocationRequest {

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;
}
