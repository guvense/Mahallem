package com.mahallem.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequest {

    @NotNull(message = "{taskId.notnull}")
    private String taskId;

    @NotNull(message = "{content.notnull}")
    private String content;
}
