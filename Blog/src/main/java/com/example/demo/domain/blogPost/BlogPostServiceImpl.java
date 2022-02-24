package com.example.demo.domain.blogPost;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository blogPostRepository;

    @Override
    public BlogPost create(BlogPost blogPost) {
        return blogPostRepository.saveAndFlush(blogPost);
    }

    @Override
    public BlogPost update(BlogPost blogPost, UUID id) {
        blogPost.setId(id);

        return blogPostRepository.saveAndFlush(blogPost);
    }

    @Override
    public BlogPost findById(UUID id) {
        return blogPostRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(UUID id) {
        blogPostRepository.deleteById(id);
    }

    @Override
    public List<BlogPost> findAll() {
        // TODO: how to get page number as param
        Pageable pageable = PageRequest.of(0, 10, Sort.by("title").ascending());
        return blogPostRepository.findAll(pageable).getContent();
    }

}
