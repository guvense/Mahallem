package com.mahallem.service.Impl;

import com.mahallem.dto.Request.AnimalRequest;
import com.mahallem.dto.Response.AnimalResponse;
import com.mahallem.entity.Animal;
import com.mahallem.entity.User;
import com.mahallem.exception.AnimalHouseIdNullException;
import com.mahallem.exception.UserNotFoundException;
import com.mahallem.mapper.AnimalMapper;
import com.mahallem.repository.AnimalRepository;
import com.mahallem.repository.UserRepository;
import com.mahallem.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

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
        animalResponse.calculateAge();
        return animalResponse;
    }


    @Override
    public Page<AnimalResponse> getAnimals(String userId, Pageable pageable) {
        User user = userRepository.getUserInfo(userId)
                                  .orElseThrow(UserNotFoundException::new);
        ObjectId houseId = user.getHouseId();
        List<Animal> animal = animalRepository.getAnimals(houseId, pageable);

        List<AnimalResponse> animalResponse = AnimalMapper.map.animalToAnimalResponseList(animal);
        animalResponse.forEach(AnimalResponse::calculateAge);

        return new PageImpl<>(animalResponse);

    }

    @Override
    public AnimalResponse deleteAnimal(String animalId, String userId) {

        // todo check use has animal
        Animal deletedAnimal = animalRepository.delete(new ObjectId(animalId));

        return AnimalMapper.map.animalToAnimalResponse(deletedAnimal);
    }


}
