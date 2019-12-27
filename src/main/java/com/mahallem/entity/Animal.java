package com.mahallem.entity;

import com.mahallem.constants.AnimalSex;
import com.mahallem.constants.AnimalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Animal extends BaseEntity{

    private AnimalType type;

    private int age;

    private AnimalSex sex;

    private ObjectId houseId;


}
