package com.blopapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts",uniqueConstraints ={@UniqueConstraint(columnNames = {"title"} )})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "decription",nullable = false)
    private String decription;

    @Column(name = "content",nullable = false)
    private String content;



   @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
     List<Comment> Comments = new ArrayList<>();

}