package com.mahallem.resource;

import com.mahallem.constants.HousePropertyType;
import com.mahallem.dto.Request.GeoLocationRequest;
import com.mahallem.dto.Request.HouseRequest;
import com.mahallem.dto.Response.GeoLocationResponse;
import com.mahallem.dto.Response.HousePropertyResponse;
import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.entity.GeoLocation;
import com.mahallem.entity.House;

import java.util.Arrays;

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


    public static HouseResponse houseResponse = HouseResponse.builder().houseStatus("Status")
                                                             .geoLocation(GeoLocationResponse.builder().latitude(10.0).longitude(10.0).build())
                                                             .grade(10.2)
                                                             .name("house")
                                                             .properties(Arrays.asList(HousePropertyResponse.builder().propertyType(HousePropertyType.ART).build())).build();

}
