package com.mahallem.dto.Response;

import com.mahallem.constants.PermissionStatus;
import com.mahallem.constants.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponse {

    protected PermissionType permissionType;

    protected String toUserId;

    protected PermissionStatus permissionStatus;
}
