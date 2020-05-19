package com.mahallem.repository.Impl;

import com.mahallem.constants.ProgressStatus;
import com.mahallem.constants.Status;
import com.mahallem.entity.Task;
import com.mahallem.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @NotNull
    private final MongoTemplate mongoTemplate;

    @Override
    public Task save(Task task) {
        return mongoTemplate.save(task);
    }

    @Override
    public Optional<Task> getTaskById(ObjectId taskId) {
        return Optional.ofNullable(mongoTemplate.findOne(Query.query(Criteria.where("_id").is(taskId)), Task.class));
    }

    @Override
    public void updateProgressStatus(ObjectId taskId, ProgressStatus progressStatus) {
        Update update = new Update().set("progressStatus", progressStatus);
        mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(taskId)), update, Task.class);
    }

    @Override
    public Optional<List<Task>> getTaskByOwnerId(ObjectId ownerId) {
        return Optional.ofNullable(mongoTemplate.find(Query.query(Criteria.where("ownerId").is(ownerId)), Task.class));
    }


    @Override
    public Optional<Task> delete(ObjectId taskId) {
        Task task = mongoTemplate.findOne(Query.query(Criteria.where("taskId").is(taskId)), Task.class);
        task.setStatus(Status.DELETED);
        mongoTemplate.save(task);
        return Optional.of(task);
    }


    @Override
    public Optional<List<Task>> getTaskByStatus(ProgressStatus progressStatus) {
        return Optional.ofNullable(mongoTemplate.find(Query.query(Criteria.where("progressStatus").is(progressStatus)), Task.class));
    }
}
