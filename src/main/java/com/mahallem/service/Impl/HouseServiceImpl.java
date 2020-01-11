package com.mahallem.service.Impl;

import com.mahallem.dto.Request.HouseRequest;
import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.entity.House;
import com.mahallem.exception.HouseNotFoundException;
import com.mahallem.repository.HouseRepository;
import com.mahallem.service.HouseService;
import com.mahallem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
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
        userService.addHouseIdToUser(userId, savedHouse.getId());

        return modelMapper.map(savedHouse, HouseResponse.class);

    }

    @Override
    public HouseResponse getHouse(String id) {

        Optional<House> houseOptional = houseRepository.getHouse(id);
        House house = houseOptional.orElseThrow(HouseNotFoundException::new);

        return modelMapper.map(house, HouseResponse.class);

    }

    @Override
    public HouseResponse getHouseWithProperties(String id) {

        return houseRepository.getHouseWithProperties(new ObjectId(id));
    }
}
