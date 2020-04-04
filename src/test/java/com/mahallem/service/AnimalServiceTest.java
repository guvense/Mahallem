package com.mahallem.service;

import com.mahallem.dto.Request.AnimalRequest;
import com.mahallem.dto.Response.AnimalResponse;
import com.mahallem.entity.Animal;
import com.mahallem.entity.User;
import com.mahallem.exception.AnimalNotFoundException;
import com.mahallem.exception.UserNotFoundException;
import com.mahallem.mapper.AnimalMapper;
import com.mahallem.repository.Impl.AnimalRepositoryImpl;
import com.mahallem.repository.Impl.UserRepositoryImpl;
import com.mahallem.resource.AnimalResource;
import com.mahallem.service.Impl.AnimalServicImpl;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.EAN;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RestClientTest(AnimalService.class)
@RunWith(MockitoJUnitRunner.class)
public class AnimalServiceTest {

    @Mock
    private AnimalRepositoryImpl animalRepository;

    @Mock
    private UserRepositoryImpl userRepository;


    @InjectMocks
    private AnimalServicImpl animalService;

    private AnimalRequest animalRequest;

    private Animal animal;

    private User user;

    private ObjectId houseId;

    @Before
    public void init() {

        when(animalRepository.save(any())).thenAnswer((Answer) invocationOnMock -> {
            Animal animal = invocationOnMock.getArgument(0);
            animal.setId(new ObjectId("5e1a436310c40031d8a7b6d9"));
            return animal;
        });

        this.houseId = new ObjectId("5e1a436310c40031d8a7b6d9");

        user = new User();
        user.setUserName("test");
        user.setHouseId(houseId);

        this.animalRequest = AnimalResource.animalRequest;

        animal = AnimalMapper.map.animalRequestToAnimal(animalRequest);
        animal.setHouseId(houseId);

        when(userRepository.getUserInfo(anyString())).thenReturn(Optional.of(user));
        animal.setId(new ObjectId("5e1a436310c40031d8a7b6d9"));
        when(animalRepository.getAnimals(any(),any())).thenReturn(Collections.singletonList(animal));

    }

    @Test(expected = UserNotFoundException.class)
    public void saveAnimal_getUserInfo_ExceptionThrown() {
        when(userRepository.getUserInfo(anyString())).thenReturn(Optional.empty());
        animalService.saveAnimal(user.getUserName(), animalRequest);
    }

    @Test
    public void saveAnimal_saveAnimalWithAllProperties() {
        AnimalResponse animalResponse = animalService.saveAnimal(user.getUserName(), animalRequest);
        assertNotNull(animalResponse.getAge());
        assertNotNull(animalResponse.getSex());
        assertNotNull(animalResponse.getType());
    }

    @Test(expected = UserNotFoundException.class)
    public void getAnimal_getUserInfo_ExceptionThrown() {
        when(userRepository.getUserInfo(anyString())).thenReturn(Optional.empty());
        animalService.getAnimals(user.getUserName(), Pageable.unpaged());
    }


    @Test
    public void getAnimal_getAnimalWithAllProperties() {
        Page<AnimalResponse> animalResponse = animalService.getAnimals(user.getUserName(), Pageable.unpaged());
        assertEquals(1,animalResponse.getContent().size());
    }



}
