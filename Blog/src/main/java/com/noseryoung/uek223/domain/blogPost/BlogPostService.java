package com.noseryoung.uek223.domain.blogPost;

import com.noseryoung.uek223.domain.blogPost.dto.UpdateBlogPostDTO;

import java.util.List;
import java.util.UUID;

public interface BlogPostService {
    BlogPost create(BlogPost blogPost);
    BlogPost update(UpdateBlogPostDTO blogPost, UUID id);
    BlogPost findById(UUID id);
    List<BlogPost> findByTitle(String title);
    void delete(UUID id);
    List<BlogPost> findAll(int page, int length);
//    BlogPost update(UUID id, BlogPost blogPost);
}
