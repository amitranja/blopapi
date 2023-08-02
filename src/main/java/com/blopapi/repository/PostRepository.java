package com.blopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blopapi.entity.Post;
public interface PostRepository extends JpaRepository<Post,Long> {

    void deleteById(long id);

}
