package com.noseryoung.uek223.domain.category;

import com.noseryoung.uek223.domain.category.dto.CategoryDTOOnlyName;
import com.noseryoung.uek223.domain.exceptions.NoCategoryFoundException;
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
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Operation(summary = "Retrieves the Categories with page and length")
    @GetMapping("/{page}/{length}")
    public ResponseEntity<List<CategoryDTOOnlyName>> findAllCategories(@PathVariable int page, @PathVariable int length) {
        return new ResponseEntity<>(categoryMapper.categoryToCategoryDTOsOnlyName(categoryService.findAll(page, length)), HttpStatus.OK);
    }

    @Operation(summary = "Retrieves the Category with the corresponding ID")
    @GetMapping("/{id}")
    public ResponseEntity<Category> findCategoryById(@Valid @PathVariable UUID id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Retrieves the Category with the corresponding Name")
    @GetMapping("/search")
    public ResponseEntity<List<Category>> getCategoryByTitle(@Valid @RequestBody String name) {

        List<Category> categories = categoryService.findByName(name);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(categories.size()));

        return ResponseEntity.ok().headers(headers).body(categories);
    }

    @PostMapping("/")
    @Operation(summary = "Needs a Category object to create a new Category")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.create(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Needs a Category and an ID to update an existing Category")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category, @PathVariable UUID id) {
        return new ResponseEntity<>(categoryService.update(category, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes the Category with the corresponding ID")
    public ResponseEntity<Category> deleteCategory(@Valid @PathVariable UUID id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NoCategoryFoundException.class)
    public ResponseEntity<String> handleCategoryNotFoundException(NoCategoryFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
