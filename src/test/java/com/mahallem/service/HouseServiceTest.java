package com.mahallem.service;

import com.mahallem.dto.Request.HouseRequest;
import com.mahallem.dto.Response.HouseResponse;
import com.mahallem.entity.House;
import com.mahallem.exception.HouseNotFoundException;
import com.mahallem.mapper.service.HouseMapper;
import com.mahallem.repository.HouseRepository;
import com.mahallem.resource.HouseResource;
import com.mahallem.service.impl.HouseServiceImpl;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


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

    private House savedHouse;

    private House existHouse;

    @Before
    public void init() {
        houseRequest = HouseResource.houseRequest;
        savedHouse = HouseResource.house;
        savedHouse.setId(new ObjectId("5e1a436310c40031d8a7b6d9"));
        when(houseRepository.save(any())).thenReturn(savedHouse);
        when(houseRepository.getHouse(any())).thenReturn(Optional.of(savedHouse));
    }


    @Test
    public void saveHouse_CreateHouse_Success() {
        doNothing().when(userService).addHouseIdToUser(any(), any());
        HouseResponse houseResponse = houseService.saveHouse(any(), houseRequest);
        assertNotNull(houseResponse.getId());
        assertNotNull(houseResponse.getName());
        assertNotNull(houseResponse.getHouseStatus());
    }


    @Test(expected = HouseNotFoundException.class)
    public void getHouse_NotExistHouse_HouseNotFoundException() {
        when(houseRepository.getHouse(any())).thenReturn(Optional.empty());
        houseService.getHouse(any());
    }

    @Test
    public void getHouse_GetHouseFromId_GetHouseWithAllFields() {
        HouseResponse houseResponse = houseService.getHouse(any());
        assertNotNull(houseResponse.getId());
        assertNotNull(houseResponse.getName());
        assertNotNull(houseResponse.getHouseStatus());
    }

    @Test
    public void updateHouse_WithSomeFields_Success(){
        existHouse = HouseResource.house;

        when(houseRepository.getHouseByUserId(any())).thenReturn(Optional.ofNullable(existHouse));
        when(houseRepository.updateHouse(any(),any())).thenReturn(savedHouse);

        HouseResponse houseResponse = houseService.updateHouse("5e1a436310c40031d8a7b6d9", houseRequest);
        assertEquals(houseRequest.getName(), houseResponse.getName());
        assertEquals(houseRequest.getHouseStatus(), houseResponse.getHouseStatus());
    }

    @Test(expected = HouseNotFoundException.class)
    public void updateHouse_NotExistHouse_HouseNotFoundException(){
        when(houseRepository.getHouseByUserId(any())).thenReturn(Optional.empty());
        houseService.updateHouse("5e1a436310c40031d8a7b6d9", houseRequest);
    }
}
