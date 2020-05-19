package com.mahallem.repository.Impl;

import com.mahallem.constants.PermissionStatus;
import com.mahallem.constants.PermissionType;
import com.mahallem.entity.Permission;
import com.mahallem.repository.PermissionRepository;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PermissionRepositoryImpl implements PermissionRepository {

    @NotNull
    private final MongoTemplate mongoTemplate;

    public Permission save(Permission permission) {
        return mongoTemplate.save(permission);
    }

    @Override
    public Optional<Permission> getPermission(ObjectId fromUserId, ObjectId toUserId, Permission permission) {

        Criteria criteria = Criteria.where("from_user_id").is(fromUserId)
                                    .and("to_user_id").is(toUserId).and("type").is(permission.getPermissionType());

        if(PermissionType.ASSIGN_TASK == permission.getPermissionType()) {
            criteria.and("taskId").is(permission.getTaskId());
        }

        return Optional.ofNullable(mongoTemplate.findOne(Query.query(criteria), Permission.class));
    }

    @Override
    public List<Permission> getAllPendingPermissions(String userId, Pageable pageable) {
        return mongoTemplate.find(Query.query(Criteria.where("to_user_id").is(new ObjectId(userId)).and("status").is(PermissionStatus.PENDING)).with(pageable), Permission.class);
    }

    @Override
    public Boolean setPermissionStatus(Permission permission, PermissionStatus permissionStatus) {
        Update update = new Update().set("status", permissionStatus);

        Criteria criteria = Criteria.where("fromUserId").is(permission.getFromUserId())
                .and("toUserId").is(permission.getToUserId())
                .and("type").is(permission.getPermissionType());

        if (null != permission.getTaskId()) {
             criteria.and("taskId").is(permission.getTaskId());

        }
        UpdateResult updateResult = mongoTemplate.updateFirst(Query.query(criteria), update, Permission.class);
        return updateResult.wasAcknowledged();
    }


}
