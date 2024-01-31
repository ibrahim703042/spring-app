package com.kwizera.ibrahim.adminPanel.dto.responseDto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponseDto {
    private long id;
    private String name;
    private List<String> bookNames;
}
