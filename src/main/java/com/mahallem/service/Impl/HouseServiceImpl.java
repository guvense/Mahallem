package com.mahallem.service.Impl;

import com.mahallem.dto.Request.HouseRequest;
import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.entity.House;
import com.mahallem.exception.HouseNotFoundException;
import com.mahallem.mapper.service.HouseMapper;
import com.mahallem.repository.HouseRepository;
import com.mahallem.service.HouseService;
import com.mahallem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    @NotNull
    private final HouseRepository houseRepository;

    private final UserService userService;

    @Override
    public HouseResponse saveHouse(String userId, HouseRequest houseRequest) {
        final House house = HouseMapper.map.houseRequestToHouse(houseRequest);
        House savedHouse = houseRepository.save(house);
        userService.addHouseIdToUser(userId, savedHouse.getId());
        return HouseMapper.map.houseToHouseResponse(savedHouse);
    }

    @Override
    public HouseResponse getHouse(String id) {
        House house = houseRepository.getHouse(id)
                .orElseThrow(HouseNotFoundException::new);
        return HouseMapper.map.houseToHouseResponse(house);
    }

    @Override
    public HouseResponse getHouseWithProperties(String id) {
        return houseRepository.getHouseWithProperties(new ObjectId(id));
    }
}
