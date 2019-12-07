package com.mahallem.DTO.Request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotBlank(message = "User name can not be blank")
    private String userName;

    @NotBlank(message = "First name can not be blank")
    private String firstName;

    @NotBlank(message = "Last name can not be blank")
    private String lastName;

    @NotBlank(message = "Password name can not be blank")
    private String password;

}
