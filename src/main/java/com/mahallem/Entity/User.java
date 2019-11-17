package com.mahallem.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{

    private String userName;

    private String firstName;

    private String lastName;

    private String sex;

    private String email;

    private String cellPhone;

    private String password;



}
