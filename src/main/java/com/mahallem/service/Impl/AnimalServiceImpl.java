package com.mahallem.service.Impl;

import com.mahallem.dto.Request.AnimalRequest;
import com.mahallem.dto.Response.AnimalResponse;
import com.mahallem.entity.Animal;
import com.mahallem.entity.User;
import com.mahallem.exception.AnimalNotFoundException;
import com.mahallem.exception.UserNotFoundException;
import com.mahallem.repository.AnimalRepository;
import com.mahallem.repository.UserRepository;
import com.mahallem.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    @NotNull
    private final ModelMapper modelMapper;

    @NotNull
    private final AnimalRepository animalRepository;

    @NotNull
    private final UserRepository userRepository;


    @Override
    public AnimalResponse saveAnimal(String userId, AnimalRequest animalRequest) {
        Optional<User> optionalUser =userRepository.getUserInfo(userId);
        User user=optionalUser.orElseThrow(UserNotFoundException::new);
        ObjectId houseId=user.getHouseId();
        final Animal animal = modelMapper.map(animalRequest, Animal.class);
        animal.setHouseId(houseId);
        Animal savedAnimal = animalRepository.save(animal);
        AnimalResponse animalResponse = modelMapper.map(savedAnimal,AnimalResponse.class);
        animalResponse.setAge(calculateAge(savedAnimal.getBirthDate()));
        return animalResponse;
    }


    @Override
    public AnimalResponse getAnimal(String id) {
        Optional<User> optionalUser =userRepository.getUserInfo(id);
        User user=optionalUser.orElseThrow(UserNotFoundException::new);
        ObjectId houseId=user.getHouseId();
        Optional<Animal> optionalAnimal = animalRepository.getAnimalByHouseId(houseId);
        Animal animal = optionalAnimal.orElseThrow(AnimalNotFoundException::new);
        AnimalResponse animalResponse = modelMapper.map(animal,AnimalResponse.class);
        animalResponse.setAge(calculateAge(animal.getBirthDate()));
        return animalResponse;

    }

    @Override
    public AnimalResponse deleteAnimal(String id) {
        Animal deletedAnimal= animalRepository.delete(new ObjectId(id));
        return modelMapper.map(deletedAnimal,AnimalResponse.class);
    }

    public int calculateAge(String birthDate){
        LocalDate localDate=LocalDate.parse(birthDate);
        LocalDate now=LocalDate.now();
        Period diff=Period.between(localDate,now);
        return  diff.getYears();
    }
}
