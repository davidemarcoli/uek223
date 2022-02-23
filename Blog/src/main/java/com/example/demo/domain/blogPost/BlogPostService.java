package com.example.demo.domain.blogPost;

import java.util.List;
import java.util.UUID;

public interface BlogPostService {
    BlogPost create(BlogPost blogPost);
    BlogPost update(BlogPost blogPost, UUID id);
    BlogPost findById(UUID id);
    void delete(UUID id);
    List<BlogPost> findAll();
//    BlogPost update(UUID id, BlogPost blogPost);
}
