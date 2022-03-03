package com.noseryoung.uek223.domain.blogpost;

import com.noseryoung.uek223.domain.blogpost.dto.UpdateBlogPostDTO;
import com.noseryoung.uek223.domain.exceptions.InvalidObjectException;
import com.noseryoung.uek223.domain.exceptions.NoAccessException;
import com.noseryoung.uek223.domain.exceptions.NoBlogPostFoundException;
import com.noseryoung.uek223.domain.blogpost.dto.BlogPostDTOPreview;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Operation(summary = "Retrieves the first ten blogposts ordered by their publish date")
    @GetMapping("/{page}/{length}")
    public ResponseEntity<List<BlogPostDTOPreview>> findAllBlogPosts
            (@PathVariable int page, @PathVariable int length) {
        return new ResponseEntity<>
                (blogPostMapper.blogToBlogDTOsPreview(blogPostService.findAll(page, length)), HttpStatus.OK);
    }

    @Operation(summary = "Retrieves the blogpost with the corresponding UUID")
    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> findBlogPostById(@Valid @PathVariable UUID id) {
        return new ResponseEntity<>(blogPostService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Retrieves blogposts that are similar to the given title")
    @GetMapping("/")
    public ResponseEntity<List<BlogPost>> getBlogPostByTitle(@Valid @RequestParam String title) {

        List<BlogPost> blogPosts = blogPostService.findByTitle(title);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(blogPosts.size()));

        return ResponseEntity.ok().headers(headers).body(blogPosts);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('CAN_CREATE_BLOG')")
    @Operation(summary = "Creates and saves a new blogpost to the database")
    public ResponseEntity<BlogPost> createBlogPost(@Valid @RequestBody BlogPost blogPost) {
        return new ResponseEntity<>(blogPostService.createBlogPost(blogPost), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CAN_MANAGE_OWN_BLOG, CAN_MANAGE_BLOG') && isAuthenticated()")
    @Operation(summary = "Updates the existing blogpost corresponding to the UUID and saves it to the database")
    public ResponseEntity<BlogPost> updateBlogPost
            (@Valid @RequestBody UpdateBlogPostDTO blogPost, @PathVariable UUID id)
            throws NoAccessException, NoBlogPostFoundException, InvalidObjectException {
        return new ResponseEntity<>(blogPostService.updateBlogPost(blogPost, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CAN_MANAGE_OWN_BLOG, CAN_MANAGE_BLOG')")
    @Operation(summary = "Deletes the blogpost with the corresponding UUID")
    public ResponseEntity<BlogPost> deleteBlogPost(@Valid @PathVariable UUID id)
            throws NoAccessException, NoBlogPostFoundException {
        blogPostService.deleteBlogPost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NoBlogPostFoundException.class)
    public ResponseEntity<String> handleNoBlogPostFoundException(NoBlogPostFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NoAccessException.class)
    public ResponseEntity<String> handleNoAccessException(NoAccessException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler(InvalidObjectException.class)
    public ResponseEntity<String> handleInvalidObjectException(InvalidObjectException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
