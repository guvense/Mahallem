package com.mahallem.service.Impl;

import com.mahallem.constants.ProgressStatus;
import com.mahallem.dto.Request.TaskRequest;
import com.mahallem.dto.Response.TaskResponse;
import com.mahallem.entity.Task;
import com.mahallem.exception.TaskNotFoundException;
import com.mahallem.exception.TaskNotFoundWithStatus;
import com.mahallem.mapper.service.TaskMapper;
import com.mahallem.repository.TaskRepository;
import com.mahallem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    @NotNull
    private final TaskRepository taskRepository;

    @Override
    public TaskResponse saveTask(TaskRequest taskRequest, String taskCreatorId) {
        Task task = TaskMapper.map.taskRequestToTask(taskRequest);
        task.setCreatorId(new ObjectId(taskCreatorId));
        Task savedTask = taskRepository.save(task);
        return TaskMapper.map.taskToTaskResponse(savedTask);
    }

    @Override
    public TaskResponse getTask(String taskId) {
        Task task = taskRepository.getTaskById(new ObjectId(taskId)).orElseThrow(TaskNotFoundException::new);
        return TaskMapper.map.taskToTaskResponse(task);
    }

    @Override
    public List<TaskResponse> getTaskByOwnerId(String ownerId) {
        List<Task> taskList = taskRepository.getTaskByOwnerId(new ObjectId(ownerId)).orElseThrow(TaskNotFoundException::new);
        return TaskMapper.map.taskListToTaskResponseList(taskList);
    }

    @Override
    public TaskResponse deleteTask(String taskId) {
        Task task = taskRepository.delete(new ObjectId(taskId)).orElseThrow(TaskNotFoundException::new);
        return TaskMapper.map.taskToTaskResponse(task);
    }

    @Override
    public TaskResponse updateTask(String taskId, TaskRequest taskRequest) {
        return null;
    }

    @Override
    public List<TaskResponse> getTaskByStatus(ProgressStatus progressStatus) {
        List<Task> taskList = taskRepository.getTaskByStatus(progressStatus).orElseThrow(TaskNotFoundWithStatus::new);
        return TaskMapper.map.taskListToTaskResponseList(taskList);
    }

    @Override
    public void updateTaskProgressStatus(String taskId, ProgressStatus progressStatus) {
        Task task = taskRepository.getTaskById(new ObjectId(taskId)).orElseThrow(TaskNotFoundException::new);
        taskRepository.updateProgressStatus(new ObjectId(taskId), progressStatus);
    }

    @Override
    public void setTaskOwner(ObjectId taskOwnerId, ObjectId taskId) {
        Task task = taskRepository.getTaskById(taskId).orElseThrow(TaskNotFoundException::new);
        task.setOwnerId(taskOwnerId);
        taskRepository.save(task);
    }
}
