package com.mahallem.dto.Request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {


    @NotBlank(message = "{username.not.blank}")
    private String userName;

    @NotBlank(message = "{first.name.not.blank}")
    private String firstName;

    @NotBlank(message = "{last.name.not.blank}")
    private String lastName;

    @NotBlank(message = "{password.not.blank}")
    private String password;

}
