package com.mahallem.entity;


import com.mahallem.constants.PermissionStatus;
import com.mahallem.constants.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission extends BaseEntity {

    @Field("from_user_id")
    private ObjectId fromUserId;

    @Field("to_user_id")
    private ObjectId toUserId;

    @Field("type")
    private PermissionType permissionType;

    @Field("status")
    private PermissionStatus permissionStatus;

    @Field("task_id")
    private ObjectId taskId;
}
