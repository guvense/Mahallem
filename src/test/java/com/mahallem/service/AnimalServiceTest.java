package com.mahallem.service;

import com.mahallem.dto.Request.AnimalRequest;
import com.mahallem.dto.Response.AnimalResponse;
import com.mahallem.entity.Animal;
import com.mahallem.entity.User;
import com.mahallem.exception.AnimalNotFoundException;
import com.mahallem.exception.UserNotFoundException;
import com.mahallem.repository.Impl.AnimalRepositoryImpl;
import com.mahallem.repository.Impl.UserRepositoryImpl;
import com.mahallem.resource.AnimalResource;
import com.mahallem.service.Impl.AnimalServiceImpl;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import java.text.SimpleDateFormat;
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
    private AnimalServiceImpl animalService;

    @Spy
    private ModelMapper modelMapper;

    private AnimalRequest animalRequest;

    private Animal animal;

    private User user;

    private ObjectId houseId;


    @Before
    public void init() {

        this.houseId = new ObjectId("5e1a436310c40031d8a7b6d9");

        user = new User();
        user.setUserName("test");
        user.setHouseId(houseId);

        this.animalRequest = AnimalResource.animalRequest;

        animal = modelMapper.map(animalRequest, Animal.class);
        animal.setHouseId(houseId);

        when(userRepository.getUserInfo(anyString())).thenReturn(Optional.of(user));
        when(animalRepository.getAnimalByHouseId(any())).thenReturn(Optional.of(animal));

    }

    @Test(expected = UserNotFoundException.class)
    public void saveAnimal_getUserInfo_ExceptionThrown() {
        when(userRepository.getUserInfo(anyString())).thenReturn(Optional.empty());
        animalService.saveAnimal(user.getUserName(), animalRequest);
    }

    @Test
    public void saveAnimal_saveAnimalWithAllProperties() {
        when(animalRepository.save(any())).thenReturn(animal);
        AnimalResponse animalResponse = animalService.saveAnimal(user.getUserName(), animalRequest);
        assertNotNull(animalResponse.getAge());
        assertNotNull(animalResponse.getSex());
        assertNotNull(animalResponse.getType());
    }

    @Test(expected = UserNotFoundException.class)
    public void getAnimal_getUserInfo_ExceptionThrown() {
        when(userRepository.getUserInfo(anyString())).thenReturn(Optional.empty());
        animalService.getAnimal(user.getUserName());
    }

    @Test(expected = AnimalNotFoundException.class)
    public void getAnimal_getAnimalWithAllProperties_ExceptionThrown() {
        when(animalRepository.getAnimalByHouseId(any())).thenReturn(Optional.empty());
        animalService.getAnimal(user.getUserName());
    }

    @Test
    public void getAnimal_getAnimalWithAllProperties() {
        AnimalResponse animalResponse = animalService.getAnimal(user.getUserName());
        assertNotNull(animalResponse.getAge());
        assertNotNull(animalResponse.getSex());
        assertNotNull(animalResponse.getType());
    }

    @Test
    public void calculateAge() {
        try {
            String valuee = "03/02/2010";
            Date currentDate = new SimpleDateFormat("dd/MM/yyyy").parse(valuee);
            int result = animalService.calculateAge(currentDate);
            assertEquals(10, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
