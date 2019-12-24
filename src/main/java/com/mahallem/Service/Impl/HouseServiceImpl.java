package com.mahallem.Service.Impl;

import com.mahallem.DTO.Request.HouseRequest;
import com.mahallem.DTO.Response.HouseResponse;
import com.mahallem.Entity.House;
import com.mahallem.Exception.HouseNotFoundException;
import com.mahallem.Repository.HouseRepository;
import com.mahallem.Service.HouseService;
import com.mahallem.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    @NotNull
    private final ModelMapper modelMapper;

    @NotNull
    private final HouseRepository houseRepository;

    private final UserService userService;

    @Override
    public HouseResponse saveHouse(String userId, HouseRequest houseRequest) {

        final House house = modelMapper.map(houseRequest, House.class);
        House savedHouse = houseRepository.save(house);
        userService.addHouseIdToUser(userId, savedHouse.get_id());

        return modelMapper.map(savedHouse, HouseResponse.class);

    }

    @Override
    public HouseResponse getHouse(String id) {

        Optional<House> houseOptional = houseRepository.findBy_id(id);
        House house = houseOptional.orElseThrow(HouseNotFoundException::new);

        return modelMapper.map(house, HouseResponse.class);

    }
}
