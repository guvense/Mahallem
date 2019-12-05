package com.mahallem.DTO.Request;

import com.mahallem.Customize.Annotation.Email;
import com.mahallem.Customize.Annotation.Phone;
import com.mahallem.Enum.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailRequest {

    @NotNull(message = "Sex is not be null")
    private Sex sex;

    @Email(message = "Wrong Email ")
    private String email;

    @Phone(message = "Wrong Phone")
    private String cellPhone;

    @NotNull(message = "Age can not be null")
    @Min(value = 18, message = "Age should not be less than 18")
    private Integer age;
}
