package com.mahallem.mapper;

import com.mahallem.dto.Request.HousePropertyRequest;
import com.mahallem.dto.Request.HouseRequest;
import com.mahallem.dto.Response.HousePropertyResponse;
import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.entity.House;
import com.mahallem.entity.HouseProperty;
import com.mahallem.mapper.customize.ObjectIdMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ObjectIdMapper.class})
public interface HouseMapper {

    HouseMapper map = Mappers.getMapper(HouseMapper.class);

    House houseRequestToHouse(HouseRequest houseRequest);

    HouseResponse houseToHouseResponse(House house);

    HouseProperty housePropertyRequestToHouseProperty(HousePropertyRequest housePropertyRequest);

    HousePropertyResponse housePropertyToHousePropertyResponse(HouseProperty houseProperty);
}
