package com.mahallem.exception;

public class TaskNotFoundWithStatus extends BaseException {
    public TaskNotFoundWithStatus() {
        super(ExceptionCode.TASK_NOT_FOUND_WITH_STATUS);
    }
}
