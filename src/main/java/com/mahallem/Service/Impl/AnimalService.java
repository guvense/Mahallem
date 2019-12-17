package com.mahallem.Service.Impl;

import com.mahallem.DTO.Request.AnimalRequest;
import com.mahallem.DTO.Response.AnimalResponse;
import com.mahallem.Entity.Animal;
import com.mahallem.Entity.House;
import com.mahallem.Entity.User;
import com.mahallem.Exception.AnimalNotFoundException;
import com.mahallem.Exception.UserNotFoundException;
import com.mahallem.Repository.AnimalRepository;
import com.mahallem.Repository.HouseRepository;
import com.mahallem.Repository.UserRepository;
import com.mahallem.Service.IAnimalService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.OptionalInt;

@Service
@RequiredArgsConstructor
public class AnimalService implements IAnimalService {

    @NotNull
    private final ModelMapper modelMapper;
    @NotNull
    private final AnimalRepository animalRepository;

    @NotNull
    private final UserRepository userRepository;

    @NotNull
    private final HouseRepository houseRepository;

    @Override
    public AnimalResponse saveAnimal(String id, AnimalRequest animalRequest) {
        final Animal animal=modelMapper.map(animalRequest,Animal.class);
        Animal savedAnimal=animalRepository.save(animal);
        AnimalResponse animalResponse=modelMapper.map(savedAnimal,AnimalResponse.class);
        return animalResponse;
    }

    @Override
    public AnimalResponse getAnimal(String id) {
        Optional<Animal> optionalAnimal=animalRepository.findBy_id(id);
        Animal animal= optionalAnimal.orElseThrow(AnimalNotFoundException::new);
        AnimalResponse animalResponse=modelMapper.map(animal,AnimalResponse.class);
        return null;
    }
}
