package com.mahallem.mapper.service;

import com.mahallem.dto.Request.CommentRequest;
import com.mahallem.dto.Response.CommentResponse;
import com.mahallem.entity.Comment;
import com.mahallem.mapper.customize.ObjectIdMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ObjectIdMapper.class)
public interface CommentMapper {

    CommentMapper map = Mappers.getMapper(CommentMapper.class);

    Comment commentRequestToComment(CommentRequest commentRequest);

    CommentResponse commentToCommentResponse(Comment comment);

    List<CommentResponse> commentListToCommentResponseList(List<Comment> commentList);


}
