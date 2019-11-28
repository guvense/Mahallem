package com.mahallem.DTO.Request;

import com.mahallem.Customize.Annotation.Email;
import com.mahallem.Customize.Annotation.Phone;
import com.mahallem.Enum.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailRequest {

    private Sex sex;

    @Email(message = "Wrong Email ")
    private String email;

    @Phone(message = "Wrong Phone")
    private String cellPhone;
}
