package com.mahallem.exception;

public class TaskNotFoundWithThisUserId extends  BaseException{

    public TaskNotFoundWithThisUserId() {
        super(ExceptionCode.TASK_NOT_FOUND_WITH_THIS_OWNER_ID);
    }
}
