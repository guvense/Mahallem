package com.mahallem.service;

import com.mahallem.dto.Request.HouseRequest;
import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.entity.House;
import com.mahallem.exception.HouseNotFoundException;
import com.mahallem.repository.HouseRepository;
import com.mahallem.resource.HouseResource;
import com.mahallem.service.Impl.HouseServiceImpl;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@RestClientTest(HouseService.class)
@RunWith(MockitoJUnitRunner.class)
public class HouseServiceTest {

    @Mock
    private HouseRepository houseRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private HouseServiceImpl houseService;

    private HouseRequest houseRequest;

    private House house;

    private HouseResponse houseResponse;

    @Before
    public void init() {
        houseRequest = HouseResource.houseRequest;
        house = HouseResource.house;
        houseResponse = HouseResource.houseResponse;

        when(houseRepository.save(any())).thenAnswer((Answer) invocationOnMock -> {
            House house = invocationOnMock.getArgument(0);
            house.setId(new ObjectId("5e1a436310c40031d8a7b6d9"));
            return house;
        });
        house.setId(new ObjectId("5e1a436310c40031d8a7b6d9"));
        when(houseRepository.getHouse(any())).thenReturn(Optional.of(house));


    }


    @Test
    public void saveHouse_CreateHouse_Success() {

        doNothing().when(userService).addHouseIdToUser(anyString(), any());

        HouseResponse houseResponse = houseService.saveHouse("5e1a436310c40031d8a7b6d9", houseRequest);

        assertNotNull(houseResponse.getId());
        assertNotNull(houseResponse.getGeoLocation());
        assertNotNull(houseResponse.getGrade());
        assertNotNull(houseResponse.getName());
        assertNotNull(houseResponse.getHouseStatus());


    }


    @Test(expected = HouseNotFoundException.class)
    public void getHouse_NotExistHouse_HouseNotFoundException() {

        when(houseRepository.getHouse(any())).thenReturn(Optional.empty());

        houseService.getHouse("5e1a436310c40031d8a7b6d9");


    }

    @Test
    public void getHouse_GetHouseFromId_GetHouseWithAllFields() {

        HouseResponse houseResponse = houseService.getHouse("5e1a436310c40031d8a7b6d9");

        assertNotNull(houseResponse.getId());
        assertNotNull(houseResponse.getGeoLocation());
        assertNotNull(houseResponse.getGrade());
        assertNotNull(houseResponse.getName());
        assertNotNull(houseResponse.getHouseStatus());

    }

    @Test
    public void a() {

        when(houseRepository.getHouseWithProperties(any())).thenReturn(houseResponse);

        HouseResponse houseWithProperties = houseService.getHouseWithProperties("5e1a436310c40031d8a7b6d9");
        assertNotNull(houseWithProperties.getHouseStatus());
        assertNotNull(houseWithProperties.getName());
        assertNotNull(houseWithProperties.getGeoLocation());
        assertNotNull(houseWithProperties.getProperties());
        assertNotNull(houseWithProperties.getGrade());




    }
}
