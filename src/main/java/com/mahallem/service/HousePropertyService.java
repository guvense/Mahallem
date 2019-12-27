package com.mahallem.service;

import com.mahallem.dto.Request.HousePropertyRequest;
import com.mahallem.dto.Response.HouseResponse;

public interface HousePropertyService {

    HouseResponse addPropertyToUserHouse(HousePropertyRequest housePropertyRequest, String userId);
}
