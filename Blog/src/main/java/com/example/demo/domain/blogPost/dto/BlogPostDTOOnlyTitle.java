package com.example.demo.domain.blogPost.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlogPostDTOOnlyTitle {
    private String title;
    private String content;

    public BlogPostDTOOnlyTitle(){}

    public BlogPostDTOOnlyTitle(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content.substring(0,30) + "...";
    }
}
