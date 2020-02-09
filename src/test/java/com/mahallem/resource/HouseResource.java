package com.mahallem.resource;

import com.mahallem.dto.Request.GeoLocationRequest;
import com.mahallem.dto.Request.HouseRequest;
import com.mahallem.entity.GeoLocation;
import com.mahallem.entity.House;

public class HouseResource {

    public static HouseRequest houseRequest = HouseRequest.builder()
                                                          .houseStatus("active")
                                                          .geoLocation(GeoLocationRequest.builder().latitude(10.0).longitude(10.0).build())
                                                          .grade(10.0)
                                                          .name("house")
                                                          .build();

    public static House house = House.builder()
                                     .houseStatus("active")
                                     .geoLocation(GeoLocation.builder().longitude(10.0).latitude(10.0).build())
                                     .grade(10.0)
                                     .name("house")
                                     .build();
}
