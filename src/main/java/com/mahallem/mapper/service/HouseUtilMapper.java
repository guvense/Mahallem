package com.mahallem.mapper.service;

import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.entity.House;
import com.mahallem.mapper.customize.ObjectIdMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {ObjectIdMapper.class})
public interface HouseUtilMapper {

    HouseResponse houseToHouseResponse(House house);
}
