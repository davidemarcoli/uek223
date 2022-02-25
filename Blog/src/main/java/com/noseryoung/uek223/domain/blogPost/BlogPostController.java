package com.noseryoung.uek223.domain.blogPost;

import com.noseryoung.uek223.domain.exceptions.NoBlogPostFoundException;
import com.noseryoung.uek223.domain.blogPost.dto.BlogPostDTOPreview;
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

    @Operation(summary = "Retrieves the first ten blogposts alphabetically ordered")
    @GetMapping("/{page}/{length}")
    public ResponseEntity<List<BlogPostDTOPreview>> findAllBlogPosts(@PathVariable int page, @PathVariable int length) {
        return new ResponseEntity<>(blogPostMapper.blogToBlogDTOsPreview(blogPostService.findAll(page, length)), HttpStatus.OK);
    }

    @Operation(summary = "Retrieves the blogpost with the corresponding UUID")
    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> findBlogPostById(@Valid @PathVariable UUID id) {
        return new ResponseEntity<>(blogPostService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Retrieves blogposts that are similar to the given title")
    @GetMapping("/search")
    public ResponseEntity<List<BlogPost>> getBlogPostByTitle(@Valid @RequestBody String title) {

        List<BlogPost> blogPosts = blogPostService.findByTitle(title);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(blogPosts.size()));

        return ResponseEntity.ok().headers(headers).body(blogPosts);
    }

    @PostMapping("/")
    @Operation(summary = "Creates and saves a new blogpost to the database")
    public ResponseEntity<BlogPost> createBlogPost(@Valid @RequestBody BlogPost blogPost) {
        return new ResponseEntity<>(blogPostService.create(blogPost), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates the existing user corresponding to the UUID and saves it to the databse")
    public ResponseEntity<BlogPost> updateBlogPost(@Valid @RequestBody BlogPost blogPost, @PathVariable UUID id) {
        return new ResponseEntity<>(blogPostService.update(blogPost, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes the blogpost with the corresponding UUID")
    public ResponseEntity<BlogPost> deleteBlogPost(@Valid @PathVariable UUID id) {
        blogPostService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NoBlogPostFoundException.class)
    public ResponseEntity<String> handleBlogPostNotFoundException(NoBlogPostFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
