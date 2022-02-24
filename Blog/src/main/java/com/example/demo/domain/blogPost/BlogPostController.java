package com.example.demo.domain.blogPost;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blog-post")
public class BlogPostController {

    private final BlogPostService blogPostService;
    private final BlogPostRepository blogPostRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<BlogPost>> findAllBlogPosts() {
        return new ResponseEntity<>(blogPostRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> findBlogPostById(@Valid @PathVariable UUID id) {
        return new ResponseEntity<>(blogPostService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/" )
    public ResponseEntity<BlogPost> createBlogPost(@Valid @RequestBody BlogPost blogPost) {
        return new ResponseEntity<>(blogPostService.create(blogPost), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@Valid @RequestBody BlogPost blogPost, @PathVariable UUID id) {
        return new ResponseEntity<>(blogPostService.update(blogPost, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BlogPost> deleteBlogPost(@Valid @PathVariable UUID id) {
        blogPostService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
