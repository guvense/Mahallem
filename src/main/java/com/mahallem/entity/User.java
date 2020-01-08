package com.mahallem.entity;

import com.mahallem.constants.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
