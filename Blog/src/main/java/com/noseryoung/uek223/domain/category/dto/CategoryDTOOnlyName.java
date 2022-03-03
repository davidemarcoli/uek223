package com.noseryoung.uek223.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTOOnlyName {
    private String name;

    public void setName(String name) {
        if (name.length() > 20) {
            this.name = name.substring(0, 20) + "...";
        } else {
            this.name = name;
        }
    }
}
