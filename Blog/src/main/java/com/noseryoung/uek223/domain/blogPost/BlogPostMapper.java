package com.noseryoung.uek223.domain.blogPost;

import com.noseryoung.uek223.domain.blogPost.dto.BlogPostDTOPreview;
import com.noseryoung.uek223.domain.category.Category;
import com.noseryoung.uek223.domain.category.dto.CategoryDTOOnlyName;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlogPostMapper {
    List<BlogPostDTOPreview> blogToBlogDTOsPreview(List<BlogPost> blogPosts);
}
