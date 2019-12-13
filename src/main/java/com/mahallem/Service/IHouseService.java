package com.mahallem.Service;


import com.mahallem.DTO.Request.HouseRequest;
import com.mahallem.DTO.Response.HouseResponse;

public interface IHouseService {

    HouseResponse saveHouse(HouseRequest houseRequest);

    HouseResponse getHouse(String name);
}
