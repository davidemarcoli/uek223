package com.example.demo.domain.blogPost;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Retrieves the first ten Blog-Posts")
    @GetMapping("/getAll")
    public ResponseEntity<List<BlogPost>> getBlogPosts() {
        return ResponseEntity.ok().body(blogPostRepository.findAll());
    }

    @Operation(summary = "Retrieves the Blog-Post with the corresponding ID")
    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPost(@Valid @PathVariable UUID id) {
        return ResponseEntity.ok().body(blogPostService.findById(id));
    }

    @Operation(summary = "Needs a BlogPost object to create a new Post")
    @PostMapping("/create" )
    public ResponseEntity<BlogPost> createBlogPost(@Valid @RequestBody BlogPost blogPost) {
        return ResponseEntity.ok().body(blogPostService.create(blogPost));
    }

    @Operation(summary = "Needs a BlogPost and an ID to update an existing Post ")
    @PutMapping("/update/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@Valid @RequestBody BlogPost blogPost, @PathVariable UUID id) {
        return ResponseEntity.ok().body(blogPostService.update(blogPost, id));
    }

    @Operation(summary = "Deletes the BlogPost with the corresponding ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BlogPost> deleteBlogPost(@Valid @PathVariable UUID id) {
        blogPostService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
