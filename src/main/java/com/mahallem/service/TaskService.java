package com.mahallem.service;

import com.mahallem.constants.ProgressStatus;
import com.mahallem.dto.Request.TaskRequest;
import com.mahallem.dto.Response.TaskResponse;
import org.bson.types.ObjectId;

import java.util.List;

public interface TaskService {

    TaskResponse saveTask(TaskRequest taskRequest,String creatorId);

    TaskResponse getTask(String taskId);

    List<TaskResponse> getTaskByOwnerId(String ownerId);

    TaskResponse deleteTask(String taskId);

    void updateTaskProgressStatus(String taskId,ProgressStatus progressStatus);

    void setTaskOwner(ObjectId taskOwnerId, ObjectId taskId);

    List<TaskResponse> getTaskByStatus(ProgressStatus progressStatus);

}
