package com.example.demo.domain.blogPost;

import com.example.demo.domain.blogPost.dto.BlogPostDTOOnlyTitle;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlogPostMapper {
    List<BlogPostDTOOnlyTitle> blogToBlogDTOsOnlyTitle(List<BlogPost> blogPosts);
}
