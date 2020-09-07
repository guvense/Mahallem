package com.mahallem.service.impl;

import com.mahallem.dto.Request.UserValidationRequest;
import com.mahallem.dto.Response.UserValidationResponse;
import com.mahallem.entity.Task;
import com.mahallem.entity.UserValidation;
import com.mahallem.exception.TaskNotFoundException;
import com.mahallem.exception.UserNotFoundException;
import com.mahallem.mapper.service.TaskMapper;
import com.mahallem.mapper.service.UserValidationMapper;
import com.mahallem.repository.UserValidationRepository;
import com.mahallem.service.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidationServiceImpl implements UserValidationService {

    private final UserValidationRepository userValidationRepository;

    @Override
    public void setEmailVerification(String userId, Boolean isEmailVerified) {
        userValidationRepository.findByUserId(new ObjectId(userId)).orElseThrow(UserNotFoundException::new);
        userValidationRepository.updateEmailVerification(new ObjectId(userId), isEmailVerified);
    }

    @Override
    public UserValidationResponse getUserValidations(String userId) {
        UserValidation userValidation = userValidationRepository.findByUserId(new ObjectId(userId)).orElseThrow(UserNotFoundException::new);
        return UserValidationMapper.map.userValidationToUserValidationResponse(userValidation);
    }

    @Override
    public UserValidationResponse saveUserValidation(UserValidationRequest userValidationRequest) {
        UserValidation userValidation = UserValidationMapper.map.userValidationRequestToUserValidation(userValidationRequest);
        UserValidation savedUserValidation = userValidationRepository.save(userValidation);
        return UserValidationMapper.map.userValidationToUserValidationResponse(savedUserValidation);
    }
}
