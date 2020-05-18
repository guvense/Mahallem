package com.mahallem.dto.Request;

import com.mahallem.constants.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionAnswerRequest {

    @NotBlank
    protected String fromUserId;

    @NotNull
    protected PermissionType permissionType;

    protected String taskId;

}
