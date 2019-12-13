package com.mahallem.Service.Impl;

import com.mahallem.DTO.Request.HouseRequest;
import com.mahallem.DTO.Response.HouseResponse;
import com.mahallem.Entity.GeoLocation;
import com.mahallem.Entity.House;
import com.mahallem.Repository.HouseRepository;
import com.mahallem.Service.IHouseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements IHouseService {

    @NotNull
    private final ModelMapper modelMapper;

    @NotNull
    private final HouseRepository houseRepository;

    public void get(){
        House house = new House();
        house.setGeoLocation(GeoLocation.builder().latitude(10).longitude(10).build());
        house.setGrade(9);
        house.setHouseStatus("test");
        house.setName("GREENWOOD");
    }

    @Override
    public HouseResponse saveHouse(HouseRequest houseRequest) {
        final House house = modelMapper.map(houseRequest, House.class);
        House savedHouse = houseRepository.save(house);
        HouseResponse houseResponse = modelMapper.map(savedHouse, HouseResponse.class);

        return houseResponse;
    }

    @Override
    public HouseResponse getHouse(String name) {
        House house = houseRepository.findByName(name);
        HouseResponse houseResponse = modelMapper.map(house, HouseResponse.class);

        return houseResponse;
    }
}
