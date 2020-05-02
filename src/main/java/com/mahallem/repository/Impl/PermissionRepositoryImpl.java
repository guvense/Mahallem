package com.mahallem.repository.Impl;

import com.mahallem.constants.PermissionType;
import com.mahallem.entity.Permission;
import com.mahallem.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
@RequiredArgsConstructor
public class PermissionRepositoryImpl implements PermissionRepository {

    @NotNull
    private final MongoTemplate mongoTemplate;

    public Permission save(Permission permission) {
        return mongoTemplate.save(permission);
    }

    @Override
    public Permission getPermission(ObjectId fromUserId, ObjectId toUserId, PermissionType permissionType) {
        return mongoTemplate.findOne(Query.query(Criteria.where("from_user_id").is(fromUserId)
                                                .and("to_user_id").is(toUserId).and("type").is(permissionType)),Permission.class);
    }
}
