package com.mahallem.entity;

import com.mahallem.constants.Sex;
import com.mahallem.constants.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 7008382284927112393L;

    private String username;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

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

    @Field("birth_date")
    private Date birthDate;

}
