package com.example.demo.domain.blogPost;

import java.util.List;
import java.util.UUID;

public interface BlogPostService {
    BlogPost save(BlogPost blogPost);
    BlogPost findById(UUID id);
    void delete(UUID id);
    List<BlogPost> findAll();
//    BlogPost update(UUID id, BlogPost blogPost);
}
