package com.mahallem.service.Impl;

import com.mahallem.dto.Request.AnimalRequest;
import com.mahallem.dto.Response.AnimalResponse;
import com.mahallem.entity.Animal;
import com.mahallem.entity.User;
import com.mahallem.exception.AnimalHouseIdNullException;
import com.mahallem.exception.AnimalNotFoundException;
import com.mahallem.exception.UserNotFoundException;
import com.mahallem.mapper.AnimalMapper;
import com.mahallem.repository.AnimalRepository;
import com.mahallem.repository.UserRepository;
import com.mahallem.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.*;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AnimalServicImpl implements AnimalService {

    @NotNull
    private final AnimalRepository animalRepository;

    @NotNull
    private final UserRepository userRepository;


    @Override
    public AnimalResponse saveAnimal(String userId, AnimalRequest animalRequest) {
        User user = userRepository.getUserInfo(userId).orElseThrow(UserNotFoundException::new);
        ObjectId houseId = user.getHouseId();
        if (null == houseId) {
            throw new AnimalHouseIdNullException();
        }

        final Animal animal = AnimalMapper.map.animalRequestToAnimal(animalRequest);
        animal.setHouseId(houseId);
        Animal savedAnimal = animalRepository.save(animal);
        AnimalResponse animalResponse = AnimalMapper.map.animalToAnimalResponse(savedAnimal);
        animalResponse.setAge(calculateAge(savedAnimal.getBirthDate()));
        return animalResponse;
    }


    @Override
    public AnimalResponse getAnimal(String userId) {
        User user = userRepository.getUserInfo(userId)
                                  .orElseThrow(UserNotFoundException::new);
        ObjectId houseId = user.getHouseId();
        Animal animal = animalRepository.getAnimalByHouseId(houseId)
                                        .orElseThrow(AnimalNotFoundException::new);
        AnimalResponse animalResponse = AnimalMapper.map.animalToAnimalResponse(animal);
        animalResponse.setAge(calculateAge(animal.getBirthDate()));
        return animalResponse;

    }

    @Override
    public AnimalResponse deleteAnimal(String animalId, String userId) {

        // todo check use has animal
        Animal deletedAnimal = animalRepository.delete(new ObjectId(animalId));

        return AnimalMapper.map.animalToAnimalResponse(deletedAnimal);
    }

    public int calculateAge(Date birthDate) {
        LocalDate now = LocalDate.now();
        LocalDate localDateBirthDate = convertDateToLocalDate(birthDate);
        Period diff = Period.between(localDateBirthDate, now);
        return diff.getYears();
    }

    private LocalDate convertDateToLocalDate(Date birthDate) {
        Instant instant = birthDate.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        return zdt.toLocalDate();
    }

}
