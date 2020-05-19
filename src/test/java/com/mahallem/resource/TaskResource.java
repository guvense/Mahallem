package com.mahallem.resource;


import com.mahallem.constants.ProgressStatus;
import com.mahallem.constants.Status;
import com.mahallem.dto.Request.TaskRequest;
import com.mahallem.entity.Task;
import org.bson.types.ObjectId;

public class TaskResource {
    public static TaskRequest taskRequest = TaskRequest.builder()
            .description("test")
            .title("test")
            .build();

    public static Task task = Task.builder()
            .description("test")
            .title("test")
            .status(Status.ACTIVE)
            .progressStatus(ProgressStatus.CREATED)
            .creatorId(new ObjectId("5eb6a49f56d8a1577c49621b"))
            .ownerId(new ObjectId("5eb6a52256d8a1577c49621d"))
            .build();
}
