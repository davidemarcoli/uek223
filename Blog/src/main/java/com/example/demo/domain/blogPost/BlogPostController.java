package com.example.demo.domain.blogPost;

import com.example.demo.domain.exceptions.NoBlogPostFoundException;
import com.example.demo.domain.blogPost.dto.BlogPostDTOOnlyTitle;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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
    private final BlogPostMapper blogPostMapper;

    @Operation(summary = "Retrieves the first ten Blog-Posts")
    @GetMapping("/getAll")
    public ResponseEntity<List<BlogPostDTOOnlyTitle>> findAllBlogPosts() {
        return new ResponseEntity<>(blogPostMapper.blogToBlogDTOsOnlyTitle(blogPostService.findAll()), HttpStatus.OK);
    }

    @Operation(summary = "Retrieves the Blog-Post with the corresponding ID")
    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> findBlogPostById(@Valid @PathVariable UUID id) {
        return new ResponseEntity<>(blogPostService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Retrieves the Blog-Post with the corresponding Title")
    @GetMapping("/search")
    public ResponseEntity<List<BlogPost>> getBlogPostByTitle(@Valid @RequestBody String title) {

        List<BlogPost> blogPosts = blogPostService.findByTitle(title);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(blogPosts.size()));

        return ResponseEntity.ok().headers(headers).body(blogPosts);
    }

    @PostMapping("/")
    @Operation(summary = "Needs a BlogPost object to create a new Post")
    public ResponseEntity<BlogPost> createBlogPost(@Valid @RequestBody BlogPost blogPost) {
        return new ResponseEntity<>(blogPostService.create(blogPost), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Needs a BlogPost and an ID to update an existing Post ")
    public ResponseEntity<BlogPost> updateBlogPost(@Valid @RequestBody BlogPost blogPost, @PathVariable UUID id) {
        return new ResponseEntity<>(blogPostService.update(blogPost, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes the BlogPost with the corresponding ID")
    public ResponseEntity<BlogPost> deleteBlogPost(@Valid @PathVariable UUID id) {
        blogPostService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NoBlogPostFoundException.class)
    public ResponseEntity<String> handleBlogPostNotFoundException(NoBlogPostFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
