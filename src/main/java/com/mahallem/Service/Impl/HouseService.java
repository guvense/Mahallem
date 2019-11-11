package com.mahallem.Service.Impl;

import com.mahallem.Entity.GeoLocation;
import com.mahallem.Entity.House;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseService {

    public void get(){
        House house = new House();
        house.setGeoLocation(GeoLocation.builder().latitude(10).longitude(10).build());
        house.setGrade(9);
        house.setHouseStatus("test");
        house.setName("GREENWOOD");
    }
}
