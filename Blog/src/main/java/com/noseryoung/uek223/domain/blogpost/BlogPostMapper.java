package com.noseryoung.uek223.domain.blogpost;

import com.noseryoung.uek223.domain.blogpost.dto.BlogPostDTO;
import com.noseryoung.uek223.domain.blogpost.dto.BlogPostDTOPreview;
import com.noseryoung.uek223.domain.blogpost.dto.UpdateBlogPostDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlogPostMapper {
    List<BlogPostDTOPreview> blogToBlogDTOPreview(List<BlogPost> blogPosts);

    BlogPost updateBlogPostDTOToBlog(UpdateBlogPostDTO blogPosts);

    List<BlogPostDTO> blogToBlogDTO(List<BlogPost> blogPosts);

    BlogPostDTO blogToBlogDTO(BlogPost blogPost);
}
