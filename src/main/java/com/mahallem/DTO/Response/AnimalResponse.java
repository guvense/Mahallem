package com.mahallem.DTO.Response;

import com.mahallem.Enum.AnimalSex;
import com.mahallem.Enum.AnimalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {

    private AnimalType type;

    private int age;

    private AnimalSex sex;

    private String houseId;
}
