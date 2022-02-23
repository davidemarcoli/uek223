package com.example.demo.domain.blogPost;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<BlogPost>> getBlogPosts() {
        return ResponseEntity.ok().body(blogPostRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPost(@Valid @PathVariable UUID id) {
        return ResponseEntity.ok().body(blogPostService.findById(id));
    }

    @PostMapping("/create" )
    public ResponseEntity<BlogPost> createBlogPost(@Valid @RequestBody BlogPost blogPost) {
        return ResponseEntity.ok().body(blogPostService.create(blogPost));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@Valid @RequestBody BlogPost blogPost, @PathVariable UUID id) {
        return ResponseEntity.ok().body(blogPostService.update(blogPost, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BlogPost> deleteBlogPost(@Valid @PathVariable UUID id) {
        blogPostService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
