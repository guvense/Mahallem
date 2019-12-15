package com.mahallem.Service.Impl;

import com.mahallem.DTO.Request.HouseRequest;
import com.mahallem.DTO.Response.HouseResponse;
import com.mahallem.Entity.GeoLocation;
import com.mahallem.Entity.House;
import com.mahallem.Entity.User;
import com.mahallem.Exception.HouseNotFoundException;
import com.mahallem.Exception.UserNotFoundException;
import com.mahallem.Repository.HouseRepository;
import com.mahallem.Service.IHouseService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements IHouseService {

    @NotNull
    private final ModelMapper modelMapper;

    @NotNull
    private final HouseRepository houseRepository;

    @Override
    public HouseResponse saveHouse(String userId, HouseRequest houseRequest) {
        final House house = modelMapper.map(houseRequest, House.class);

        House savedHouse = houseRepository.save(house);
        savedHouse.get_id();

        HouseResponse houseResponse = modelMapper.map(savedHouse, HouseResponse.class);

        return houseResponse;
    }

    @Override
    public HouseResponse getHouse(String id) {
        Optional<House> houseOptional = houseRepository.findBy_id(id);
        House house = houseOptional.orElseThrow(HouseNotFoundException::new);

        HouseResponse houseResponse = modelMapper.map(house, HouseResponse.class);

        return houseResponse;
    }
}
