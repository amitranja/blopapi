package com.blopapi.service.impl;

import com.blopapi.entity.Post;
import com.blopapi.exceptions.ResourceNotFoundException;
import com.blopapi.payload.PostDto;
import com.blopapi.repository.PostRepository;
import com.blopapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceimpl implements PostService {

    private PostRepository postRepo;

    private ModelMapper modelMapper;


    public PostServiceimpl(PostRepository postRepo , ModelMapper  modelMapper) {
        this.postRepo = postRepo;
        this.modelMapper=modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post Post = mapToEntity(postDto);

        com.blopapi.entity.Post savePost = postRepo.save(Post);
        PostDto dto = mapToDto(savePost);
        
        return dto;
    }

    @Override
    public PostDto getPostById(long id) {
       Post Post= postRepo.findById(id).orElseThrow(
               ()->new ResourceNotFoundException(id)
               );

        PostDto dto = mapToDto(Post);
        return dto;
    }

    @Override
    public List<PostDto> getAllPost(int pageNo, int pageSize, String shortBy, String shortDir) {

        Sort sort= shortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(shortBy).ascending():Sort.by(shortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> Posts = postRepo.findAll(pageable);

        List<Post> content = Posts.getContent();

        List<PostDto> dtos = content.stream().map(Post -> mapToDto(Post)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public void deletePost(long id) {
        Post DEL = postRepo.findById(id).orElseThrow(

                () -> new ResourceNotFoundException(id)
        );
        postRepo.deleteById(id);
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        postRepo.findById(id).orElseThrow(

                ()-> new ResourceNotFoundException(id)
        );

      Post updateContent = mapToEntity(postDto);

        updateContent.setId(postDto.getId());

        Post updateInfo = postRepo.save(updateContent);

        return mapToDto(updateInfo);
    }



    PostDto mapToDto(Post Post){


      PostDto dto =  modelMapper.map(Post , PostDto.class);
//        dto.setId(Post.getId());
//        dto.setTitle(Post.getTitle());
//        dto.setDecription(Post.getDecription());
//        dto.setContent(Post.getContent());
        return dto;
    }
    
    
    Post mapToEntity(PostDto postDto){

     Post Post =   modelMapper.map(postDto , com.blopapi.entity.Post.class);
//        Post.setTitle(postDto.getTitle());
//        Post.setDecription(postDto.getDecription());
//        Post.setContent(postDto.getContent());
        return Post;
    }
}
