package com.mahallem.Service;


import com.mahallem.DTO.Request.HouseRequest;
import com.mahallem.DTO.Response.HouseResponse;

public interface HouseService {

    HouseResponse saveHouse(String userId, HouseRequest houseRequest);

    HouseResponse getHouse(String id);
}
