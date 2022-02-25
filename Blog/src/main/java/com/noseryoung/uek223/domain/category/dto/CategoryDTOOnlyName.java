package com.noseryoung.uek223.domain.category.dto;

import com.noseryoung.uek223.domain.category.Category;
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
public class CategoryDTOOnlyName {
    private String name;

    public void setName(String name) {
        if (name.length() > 20)    {
            this.name = name.substring(0,20) + "...";
        }  else {
            this.name = name;
        }
    }
}
