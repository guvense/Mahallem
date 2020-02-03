package com.mahallem.dto.Request;

import com.mahallem.constants.AnimalSex;
import com.mahallem.constants.AnimalType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalRequest {

    @NotNull(message = "Animal type cannot be null")
    private AnimalType type;

    @NotNull(message = "Animal sex cannot be null")
    private AnimalSex sex;

    @NotNull(message = "Animal birthdate cannot be null")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;


}
