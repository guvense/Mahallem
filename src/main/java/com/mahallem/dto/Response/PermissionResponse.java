package com.mahallem.dto.Response;

import com.mahallem.constants.PermissionStatus;
import com.mahallem.constants.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponse {

    protected PermissionType permissionType;

    protected String toUserId;

    protected String fromUserId;

    protected PermissionStatus permissionStatus;

    protected String taskId;
}
