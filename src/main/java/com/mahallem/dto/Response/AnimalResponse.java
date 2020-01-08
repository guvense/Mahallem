package com.mahallem.dto.Response;

import com.mahallem.constants.AnimalSex;
import com.mahallem.constants.AnimalType;
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

}
