package com.noseryoung.uek223.domain.blogpost.dto;

import com.noseryoung.uek223.domain.category.dto.CategoryDTOOnlyName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostDTOPreview {
    private String title;
    private String content;
    private Set<CategoryDTOOnlyName> category;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

    public void setContent(String content) {
        this.content = content.substring(0, 30) + "...";
    }
}
