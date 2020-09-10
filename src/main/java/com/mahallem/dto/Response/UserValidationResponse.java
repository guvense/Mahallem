package com.mahallem.dto.Response;

import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserValidationResponse {

    private String id;

    private ObjectId userId;

    private Boolean isEmailVerified;

}
