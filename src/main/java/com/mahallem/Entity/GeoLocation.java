package com.mahallem.Entity;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeoLocation {

    private double latitude;
    private double longitude;
}
