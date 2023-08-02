package com.blopapi.service;

import com.blopapi.payload.PostDto;

import java.util.List;

public interface PostService {


    public PostDto createPost(PostDto postDto);


    PostDto getPostById(long id);

    List<PostDto> getAllPost(int pageNo, int pageSize, String shortBy, String shortDir);

    void deletePost(long id);

    PostDto updatePost(long id , PostDto postDto);

}
