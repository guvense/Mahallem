package com.mahallem.service;

import com.mahallem.dto.Request.UserValidationRequest;
import com.mahallem.dto.Response.UserValidationResponse;
import com.mahallem.entity.UserValidation;

public interface UserValidationService {

    void setEmailVerification(String userId, Boolean isEmailVerified);

    UserValidationResponse getUserValidations(String userId);

    UserValidationResponse saveUserValidation(UserValidationRequest userValidationRequest);
}
