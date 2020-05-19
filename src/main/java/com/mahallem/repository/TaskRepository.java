package com.mahallem.repository;

import com.mahallem.constants.ProgressStatus;
import com.mahallem.entity.Task;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Task save(Task task);

    Optional<Task> getTaskById(ObjectId taskId);

    Optional<List<Task>> getTaskByOwnerId(ObjectId ownerId);

    Optional<Task> delete(ObjectId taskId);

    void updateProgressStatus(ObjectId taskId,ProgressStatus progressStatus);

    Optional<List<Task>> getTaskByStatus(ProgressStatus progressStatus);
}
