package com.blopapi.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {
    private long id;

    @NotEmpty(message = "title is empty")
    @Size(min = 2, message = " title should be atleast two character")
    private String title;

    @NotEmpty(message = "decription is empty")
    @Size(min = 4, message = "decription should be atleast four character")
    private String decription;

    @NotEmpty(message = "content must not be empty")
    private String content;
}
