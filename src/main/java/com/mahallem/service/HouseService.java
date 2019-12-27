package com.mahallem.service;


import com.mahallem.dto.Request.HouseRequest;
import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.entity.House;
import org.bson.types.ObjectId;

public interface HouseService {

    HouseResponse saveHouse(String userId, HouseRequest houseRequest);

    HouseResponse getHouse(String id);

    HouseResponse getHouseWithProperties(String id);
}
