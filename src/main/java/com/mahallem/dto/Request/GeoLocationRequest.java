package com.mahallem.dto.Request;

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
