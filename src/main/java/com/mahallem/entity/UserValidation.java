package com.mahallem.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserValidation {

    @Field("user_id")
    private ObjectId userId;

    @Field("is_email_verified")
    private Boolean isEmailVerified;
}
