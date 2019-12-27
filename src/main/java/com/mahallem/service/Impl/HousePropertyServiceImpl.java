package com.mahallem.service.Impl;

import com.mahallem.dto.Request.HousePropertyRequest;
import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.dto.Response.UserResponse;
import com.mahallem.entity.HouseProperty;
import com.mahallem.exception.HousePropertyExistException;
import com.mahallem.repository.PropertyRepository;
import com.mahallem.service.HousePropertyService;
import com.mahallem.service.HouseService;
import com.mahallem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HousePropertyServiceImpl implements HousePropertyService {

    private final PropertyRepository propertyRepository;

    private final UserService userService;

    private final ModelMapper modelMapper;

    private final HouseService houseService;


    @Override
    public HouseResponse addPropertyToUserHouse(HousePropertyRequest housePropertyRequest, String userId) {

        HouseProperty houseProperty = modelMapper.map(housePropertyRequest, HouseProperty.class);
        String houseId = userService.getHouseId(userId);
        houseProperty.set_houseId(new ObjectId(houseId));
        checkPropertyExist(houseProperty);
        propertyRepository.save(houseProperty);

        return houseService.getHouseWithProperties(houseId);

    }

    private void checkPropertyExist(HouseProperty houseProperty) {
        boolean propertyExist = propertyRepository.isPropertyExist(houseProperty);
        if(propertyExist) {
            throw new HousePropertyExistException();
        }
    }
}
