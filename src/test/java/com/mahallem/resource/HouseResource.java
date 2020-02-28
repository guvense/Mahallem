package com.mahallem.resource;

import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.entity.House;

public class HouseResource {
    public static HouseResponse houseResponse = HouseResponse.builder().name("test").houseStatus("test").id("test").build();
    public static House house = House.builder().houseStatus("test").name("test").build();
}
