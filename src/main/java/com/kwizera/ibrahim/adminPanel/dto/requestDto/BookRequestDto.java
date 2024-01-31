package com.kwizera.ibrahim.adminPanel.dto.requestDto;

import lombok.Data;

import java.util.List;

@Data
public class BookRequestDto {
    private String name;
    private List<Long> authorId;
    private long categoryId;
}
