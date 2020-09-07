package com.mahallem.dto.Request;

import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserValidationRequest {

    private ObjectId userId;

    private Boolean isEmailVerified;
}
