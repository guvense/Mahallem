package com.mahallem.dto.Request;

import com.mahallem.constants.Sex;
import com.mahallem.customize.Annotation.Email;
import com.mahallem.customize.Annotation.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    private String profilePictureURL;
}
