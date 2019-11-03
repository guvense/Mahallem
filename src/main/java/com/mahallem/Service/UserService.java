package com.mahallem.Service;

import com.mahallem.Entity.GeoLocation;
import com.mahallem.Entity.House;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    public void get(){

        House house = new House();
        house.setGeoLocation(GeoLocation.builder().latitude(10).longitude(10).build());
        house.setName("deneme");
        house.setGrade(10);
    }
}
