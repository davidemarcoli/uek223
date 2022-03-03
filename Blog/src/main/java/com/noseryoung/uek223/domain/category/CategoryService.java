package com.noseryoung.uek223.domain.category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category create(Category blogPost);

    Category update(Category blogPost, UUID id);

    Category findById(UUID id);

    List<Category> findByName(String title);

    void delete(UUID id);

    List<Category> findAll(int page, int length);
}
