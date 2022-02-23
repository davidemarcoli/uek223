package com.example.demo.domain.blogPost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blog-post")
public class BlogPostController {

    private BlogPostService blogPostService;
    private BlogPostRepository blogPostRepository;

    @GetMapping("/getAll")
    public List<BlogPost> getBlogPosts() {
        return blogPostRepository.findAll();
    }

    @GetMapping("/{id}")
    public BlogPost getBlogPost(@PathVariable UUID id) {
        return blogPostService.findById(id);
    }
}
