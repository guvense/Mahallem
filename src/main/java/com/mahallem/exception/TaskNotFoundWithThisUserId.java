package com.mahallem.exception;

public class TaskNotFoundWithThisUserId extends  BaseException{

    public TaskNotFoundWithThisUserId() {
        super(ExceptionCode.TASK_NOT_FOUND_WİTH_THIS_OWNER_ID);
    }
}
