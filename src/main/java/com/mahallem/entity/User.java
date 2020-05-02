package com.mahallem.entity;

import com.mahallem.constants.Sex;
import com.mahallem.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3504972142712457030L;

    private String username;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    private Sex sex;

    private String email;

    @Field("cell_phone")
    private String cellPhone;

    private String password;

    @Field("house_id")
    private ObjectId houseId;

    private Integer age;

    private House house;

    private List<Animal> animals;

    private Status status;
}
