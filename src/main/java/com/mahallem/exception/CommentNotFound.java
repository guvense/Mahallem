package com.mahallem.exception;

public class CommentNotFound extends BaseException{
    public CommentNotFound() {
        super(ExceptionCode.COMMENT_NOT_FOUND);
    }
}
