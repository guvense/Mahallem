package com.mahallem.DTO.Response;


import com.mahallem.Customize.Annotation.Phone;
import com.mahallem.Enum.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String firstName;

    private String lastName;

    private Sex sex;

    @Email(message = "Wrong Email Format")
    private String email;

    @Phone
    private String cellPhone;
}
