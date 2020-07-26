package com.mahallem.dto.Request;


import com.mahallem.customize.Annotation.Email;
import com.mahallem.customize.Annotation.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailRequest {

    private String email;

    private String cellPhone;

    private String firstName;

    private String lastName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;
}
