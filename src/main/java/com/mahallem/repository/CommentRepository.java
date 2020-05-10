package com.mahallem.repository;

import com.mahallem.entity.Comment;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> delete(ObjectId commentId);

    List<Comment> getCommentByTaskId(ObjectId taskId);
}
