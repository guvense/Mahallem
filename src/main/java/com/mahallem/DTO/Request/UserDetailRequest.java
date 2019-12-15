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

    @NotNull(message = "{sex.notnull}")
    private Sex sex;

    @Email
    private String email;

    @Phone
    private String cellPhone;

    @NotNull(message = "{age.notnull}")
    @Min(value = 18, message = "{age.should.be.greater}")
    private Integer age;
}
