package com.noseryoung.uek223.domain.blogpost.dto;

import com.noseryoung.uek223.domain.appuser.User;
import com.noseryoung.uek223.domain.appuser.dto.UserDTOOnlyUsername;
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
public class BlogPostDTO {
    private String title;
    private String content;
    private Set<CategoryDTOOnlyName> category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDTOOnlyUsername author;

}
