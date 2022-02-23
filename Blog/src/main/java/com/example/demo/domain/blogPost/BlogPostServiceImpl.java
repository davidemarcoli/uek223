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

    private BlogPostRepository blogPostRepository;

    @Override
    public BlogPost save(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
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
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        return blogPostRepository.findAll(pageable).getContent();
    }
}
