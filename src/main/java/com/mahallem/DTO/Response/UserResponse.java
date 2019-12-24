package com.mahallem.DTO.Response;


import com.mahallem.Customize.Annotation.Phone;
import com.mahallem.Entity.Animal;
import com.mahallem.Enum.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String firstName;

    private String userName;

    private String lastName;

    private Sex sex;

    private String email;

    private String cellPhone;

    private String houseId;

    private HouseResponse houseResponse;

    private Integer age;

    private List<AnimalResponse> animals;
}
