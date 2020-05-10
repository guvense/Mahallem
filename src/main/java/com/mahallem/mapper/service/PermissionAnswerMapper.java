package com.mahallem.mapper.service;

import com.mahallem.dto.Request.PermissionAnswerRequest;
import com.mahallem.entity.Permission;
import org.bson.types.ObjectId;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionAnswerMapper {

    PermissionAnswerMapper map = Mappers.getMapper(PermissionAnswerMapper.class);

    @Mappings({
            @Mapping(source = "permissionAnswerRequest", target = "fromUserId", qualifiedByName = "fromUserId"),
            @Mapping(source = "permissionAnswerRequest", target = "taskId", qualifiedByName = "toTaskId"),
    })
    Permission permissionAnswerRequestToPermission(PermissionAnswerRequest permissionAnswerRequest, @Context String toUserId);

    @AfterMapping
    default void setToUserId(@MappingTarget Permission.PermissionBuilder permission, @Context String toUserId) {
        permission.toUserId(new ObjectId(toUserId));
    }

    @Named("fromUserId")
    default ObjectId toFromUserObjectId(PermissionAnswerRequest permissionAnswerRequest) {

        return new ObjectId(permissionAnswerRequest.getFromUserId());
    }

    @Named("toTaskId")
    default ObjectId toTaskObjectId(PermissionAnswerRequest permissionAnswerRequest) {
        if (null == permissionAnswerRequest.getTaskId())
            return null;
        return new ObjectId(permissionAnswerRequest.getTaskId());
    }
}
