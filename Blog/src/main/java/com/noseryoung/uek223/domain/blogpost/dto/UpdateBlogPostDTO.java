package com.noseryoung.uek223.domain.blogpost.dto;

import com.noseryoung.uek223.domain.category.dto.CategoryDTOOnlyName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBlogPostDTO {

    @Size(min = 10, max = 100)
    private String title;

    @Size(min = 30, max = 65535)
    private String content;

    private Set<CategoryDTOOnlyName> category;

}
