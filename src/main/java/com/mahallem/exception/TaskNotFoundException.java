package com.mahallem.exception;

public class TaskNotFoundException extends BaseException {
    public TaskNotFoundException() {
        super(ExceptionCode.TASK_NOT_FOUND);
    }
}
