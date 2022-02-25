package com.noseryoung.uek223.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class LevenshteinResult {
    private Object source;
    private Integer distance;
}
