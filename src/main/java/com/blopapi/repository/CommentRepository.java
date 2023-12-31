package com.blopapi.repository;

import com.blopapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository <Comment,Long> {

    List<Comment> findByPostId(long id);
}
