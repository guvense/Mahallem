package com.mahallem.mapper.service;


import com.mahallem.constants.PermissionStatus;
import com.mahallem.dto.Request.PermissionRequest;
import com.mahallem.dto.Response.PermissionResponse;
import com.mahallem.entity.Permission;
import com.mahallem.mapper.customize.ObjectIdMapper;
import com.mahallem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        uses = {ObjectIdMapper.class, UserService.class},
        componentModel = "spring")
@RequiredArgsConstructor
public abstract class PermissionMapper {

    @Autowired
    protected UserService userService;

    @Mappings({
            @Mapping(target = "toUserId", expression = "java(userService.getUserIdFromUsername(permissionRequest.getToUserName()))"),
            @Mapping(source = "permissionRequest", target = "taskId", qualifiedByName = "toTaskId"),
    })
    public abstract Permission permissionRequestToPermission(PermissionRequest permissionRequest, @Context String fromUserId);

    @AfterMapping
    void setFromUserId(@MappingTarget Permission.PermissionBuilder permission, @Context String fromUserId) {
        permission.fromUserId(new ObjectId(fromUserId));
    }

    @AfterMapping
    void initializeStatus(@MappingTarget Permission.PermissionBuilder permission) {
        permission.permissionStatus(PermissionStatus.PENDING);
    }

    @Named("toTaskId")
    ObjectId toObjectId(PermissionRequest permissionRequest) {

        if (null == permissionRequest.getTaskId()) {
            return null;
        }
        return new ObjectId(permissionRequest.getTaskId());
    }

    public abstract PermissionResponse permissionToPermissionResponse(Permission permission);

    public abstract List<PermissionResponse> permissionToPermissionResponse(List<Permission> permission);
}
