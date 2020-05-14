package com.mahallem.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {

    @NotNull(message = "{title.notnull}")
    private String title;

    @NotNull(message = "{description.notnull}")
    private String description;

    @NotNull(message = "{deadline.notnull}")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date deadline;

}
