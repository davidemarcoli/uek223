package com.noseryoung.uek223.domain.blogpost;

import com.noseryoung.uek223.domain.blogpost.dto.BlogPostDTO;
import com.noseryoung.uek223.domain.blogpost.dto.UpdateBlogPostDTO;
import com.noseryoung.uek223.domain.exceptions.InvalidObjectException;
import com.noseryoung.uek223.domain.exceptions.NoAccessException;

import java.util.List;
import java.util.UUID;

public interface BlogPostService {
    BlogPost createBlogPost(BlogPost blogPost);

    BlogPost updateBlogPost(UpdateBlogPostDTO blogPost, UUID id) throws NoAccessException, InvalidObjectException;

    BlogPost findById(UUID id);

    List<BlogPostDTO> findByTitle(String title);

    void deleteBlogPost(UUID id) throws NoAccessException;

    List<BlogPost> findAll(int page, int length);
}
