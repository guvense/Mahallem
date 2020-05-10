package com.mahallem.controller;

import com.mahallem.dto.Request.TaskRequest;
import com.mahallem.dto.Response.TaskResponse;
import com.mahallem.service.TaskService;
import com.mahallem.util.JwtUtil;
import com.mahallem.util.ResponseUtil;
import com.mahallem.viewmodel.MainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create-task")
    public ResponseEntity<MainResponse<TaskResponse>> createTask(@Valid @RequestBody TaskRequest taskRequest, HttpServletRequest httpServletRequest){
        String taskCreatorId= JwtUtil.getObjectIdFromRequest(httpServletRequest);
        TaskResponse taskResponse=taskService.saveTask(taskRequest,taskCreatorId);
        return ResponseUtil.data(taskResponse);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<MainResponse<TaskResponse>> taskDetail(@PathVariable String taskId){
        return ResponseUtil.data(taskService.getTask(taskId));
    }

    @GetMapping("/all-task")
    public ResponseEntity<MainResponse<List<TaskResponse>>> getAllTask(HttpServletRequest httpServletRequest){
        String taskOwnerId=JwtUtil.getObjectIdFromRequest(httpServletRequest);
        return ResponseUtil.data(taskService.getTaskByOwnerId(taskOwnerId));
    }
}
