package com.mahallem.repository.Impl;

import com.mahallem.entity.Comment;
import com.mahallem.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @NotNull
    private MongoTemplate mongoTemplate;

    @Override
    public Comment save(Comment comment) {
        return mongoTemplate.save(comment);
    }

    @Override
    public Optional<Comment> delete(ObjectId commentId) {
        return Optional.ofNullable(mongoTemplate.findAndRemove(Query.query(Criteria.where("comment_id").is(commentId)), Comment.class));
    }


    @Override
    public List<Comment> getCommentByTaskId(ObjectId taskId) {
        return mongoTemplate.find(Query.query(Criteria.where("task_id").is(taskId)), Comment.class);
    }
}
