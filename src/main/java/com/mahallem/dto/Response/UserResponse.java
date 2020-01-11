package com.mahallem.dto.Response;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mahallem.constants.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserResponse implements Serializable {

    private static final long serialVersionUID = 2110166895723582302L;

    private String _id;

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
