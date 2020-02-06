package com.mahallem.entity;

import com.mahallem.constants.AnimalSex;
import com.mahallem.constants.AnimalType;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Animal extends BaseEntity {

    private AnimalType type;

    private AnimalSex sex;

    private ObjectId houseId;

    private Date birthDate;

}
