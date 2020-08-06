package com.mahallem.service;


import com.mahallem.constants.ProgressStatus;
import com.mahallem.constants.Status;
import com.mahallem.dto.Request.TaskRequest;
import com.mahallem.dto.Response.TaskResponse;
import com.mahallem.entity.Task;
import com.mahallem.exception.TaskNotFoundException;
import com.mahallem.exception.TaskNotFoundWithStatus;
import com.mahallem.exception.TaskNotFoundWithThisUserId;
import com.mahallem.repository.Impl.TaskRepositoryImpl;
import com.mahallem.resource.TaskResource;
import com.mahallem.service.impl.TaskServiceImpl;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RestClientTest(TaskService.class)
@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @Mock
    private TaskRepositoryImpl taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;

    private TaskRequest taskRequest;

    private String taskId;

    private String taskCreatorId;

    private String taskOwnerId;

    @Captor
    ArgumentCaptor<Task> captor;

    @Before
    public void init() {
        taskId = "5eb8054656d8a15448079ba0";
        taskCreatorId = "5eb8054656d8a15448079ba0";
        taskOwnerId = "5eb6a52256d8a1577c49621d";
        task = TaskResource.task;
        task.setId(new ObjectId(taskId));
        taskRequest = TaskResource.taskRequest;
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        when(taskRepository.save(any())).thenReturn(task);
        when(taskRepository.getTaskById(any())).thenReturn(Optional.of(task));
        when(taskRepository.getTaskByOwnerId(any())).thenReturn(Optional.of(taskList));
        when(taskRepository.delete(any())).thenReturn(Optional.of(task));
        when(taskRepository.getTaskByStatus(any())).thenReturn(Optional.of(taskList));
    }

    @Test
    public void saveTask_createTask_success() {
        TaskResponse taskResponse = taskService.saveTask(taskRequest, taskCreatorId);
        assertEquals("test", taskResponse.getDescription());
        assertEquals("test", taskResponse.getTitle());
        assertEquals(taskCreatorId, taskResponse.getId());
        assertEquals(ProgressStatus.CREATED, taskResponse.getProgressStatus());
    }

    @Test
    public void getTask_getTaskById_getTaskWithAllProperties() {
        TaskResponse taskResponse = taskService.getTask(taskId);
        assertEquals("test", taskResponse.getDescription());
        assertEquals("test", taskResponse.getTitle());
        assertEquals(taskCreatorId, taskResponse.getId());
        assertEquals(ProgressStatus.CREATED, taskResponse.getProgressStatus());
    }

    @Test(expected = TaskNotFoundException.class)
    public void getTask_TaskNotFoundException() {
        when(taskRepository.getTaskById(any())).thenReturn(Optional.empty());
        taskService.getTask(taskId);
    }

    @Test
    public void getTask_getAllTaskByOwnerId_getTaskWithAllProperties() {
        List<TaskResponse> taskResponses = taskService.getTaskByOwnerId(taskOwnerId);
        assertEquals(1, taskResponses.size());
        assertEquals("test", taskResponses.get(0).getDescription());
    }

    @Test(expected = TaskNotFoundWithThisUserId.class)
    public void getTask_getAllTaskByOwnerId_TaskNotFoundWithThisUserId() {
        when(taskRepository.getTaskByOwnerId(any())).thenReturn(Optional.empty());
        taskService.getTaskByOwnerId(taskOwnerId);
    }

    @Test
    public void deleteTask_deleteTaskById_setTaskStatusToDeleted() {
        task.setStatus(Status.DELETED);
        TaskResponse taskResponse = taskService.deleteTask(taskId);
        assertEquals(Status.DELETED, taskResponse.getStatus());
    }

    @Test(expected = TaskNotFoundException.class)
    public void deleteTask_deleteTaskById_taskNotFoundWithThisId_TaskNotFoundException() {
        when(taskRepository.delete(any())).thenReturn(Optional.empty());
        taskService.deleteTask(taskId);
    }

    @Test
    public void getTaskByStatus_listCreatedTask_getTaskWithProperties() {
        List<TaskResponse> taskResponses = taskService.getTaskByStatus(ProgressStatus.CREATED);
        assertEquals(1, taskResponses.size());
        assertEquals(ProgressStatus.CREATED, taskResponses.get(0).getProgressStatus());
    }

    @Test(expected = TaskNotFoundWithStatus.class)
    public void getTaskByStatus_TaskNotFoundWithThisStatus_ThrowTaskNotFoundWithStatusException() {
        when(taskRepository.getTaskByStatus(any())).thenReturn(Optional.empty());
        taskService.getTaskByStatus(any());
    }

    @Test
    public void setTaskOwner_setTaskOwnerId_getTaskWithOwnerId() {

        ObjectId taskOwnerId = new ObjectId("5eb6a52256d8a1577c49621c");
        taskService.setTaskOwner(taskOwnerId, new ObjectId(taskId));
        verify(taskRepository).save(captor.capture());
        assertEquals(taskOwnerId, captor.getValue().getOwnerId());

    }

}
