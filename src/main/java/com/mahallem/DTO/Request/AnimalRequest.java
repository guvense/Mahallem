package com.mahallem.DTO.Request;

import com.mahallem.Enum.AnimalSex;
import com.mahallem.Enum.AnimalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalRequest {

    @NotNull(message = "Animal type cannot be null")
    private AnimalType type;

    @NotNull(message = "Animal age cannot be null")
    private int age;

    @NotNull(message = "Animal sex cannot be null")
    private AnimalSex sex;
}
