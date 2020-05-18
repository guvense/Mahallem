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
public class PermissionRequest {

    @NotNull
    protected PermissionType permissionType;

    @NotBlank
    protected String toUserName;

    protected String taskId;
}
