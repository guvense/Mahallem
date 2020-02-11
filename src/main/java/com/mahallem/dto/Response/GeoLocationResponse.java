package com.mahallem.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GeoLocationResponse {

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;
}