package com.blopapi.service;

import com.blopapi.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long post_id, CommentDto commentDto);
    public List<CommentDto> findCommentByPostId(long postId);

    void deleteCommentById(long postId, long id);

    CommentDto getCommentById(long postId, long id);

    CommentDto updateComment(Long postId, Long id, CommentDto commentDto);
}