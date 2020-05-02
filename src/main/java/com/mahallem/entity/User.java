package com.mahallem.entity;

import com.mahallem.constants.Sex;
import com.mahallem.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3504972142712457030L;

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

    private List<Animal> animals;

    private Status status;
}
