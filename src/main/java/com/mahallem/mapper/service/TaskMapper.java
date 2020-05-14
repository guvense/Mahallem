package com.mahallem.mapper.service;


import com.mahallem.constants.ProgressStatus;
import com.mahallem.constants.Status;
import com.mahallem.dto.Request.TaskRequest;
import com.mahallem.dto.Response.TaskResponse;
import com.mahallem.entity.Task;
import com.mahallem.mapper.customize.ObjectIdMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ObjectIdMapper.class)
public interface TaskMapper {

    TaskMapper map = Mappers.getMapper(TaskMapper.class);

    Task taskRequestToTask(TaskRequest taskRequest);

    TaskResponse taskToTaskResponse(Task task);

    List<TaskResponse> taskListToTaskResponseList(List<Task> task);

    @AfterMapping
    default void initializeStatusAndProgress(@MappingTarget Task.TaskBuilder task) {
        task.status(Status.ACTIVE);
        task.progressStatus(ProgressStatus.CREATED);
    }
}
