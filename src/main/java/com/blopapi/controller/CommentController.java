package com.blopapi.controller;

import com.blopapi.payload.CommentDto;
import com.blopapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //http://localhost:8080:/api/posts/1/comments

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> findCommentsByPostId(@PathVariable(value = "postId") long postId) {
        List<CommentDto> dtos = commentService.findCommentByPostId(postId);
        return dtos;
    }

    //http://localhost:8080/api/posts/1/comments/1
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long id) {
        commentService.deleteCommentById(postId, id);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long id) {
        CommentDto commentDto = commentService.getCommentById(postId, id);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "id") Long id,
                                                    @RequestBody CommentDto commentDto) {
        CommentDto updatedComment = commentService.updateComment(postId,
                id, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }
}

