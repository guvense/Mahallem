package com.mahallem.DbResult;

import com.mahallem.Entity.House;
import com.mahallem.Enum.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResult {

    private String userName;

    private String firstName;

    private String lastName;

    private Sex sex;

    private String email;

    private String cellPhone;

    private String password;

    private ObjectId houseId;

    private Integer age;

    private House house;
}
