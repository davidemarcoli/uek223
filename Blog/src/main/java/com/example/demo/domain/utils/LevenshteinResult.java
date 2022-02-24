package com.example.demo.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class LevenshteinResult {
    private Object source;
    private Integer distance;
}
