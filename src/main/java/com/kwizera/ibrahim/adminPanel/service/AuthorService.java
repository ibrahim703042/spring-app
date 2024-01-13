package com.kwizera.ibrahim.adminPanel.service;

import com.kwizera.ibrahim.adminPanel.dto.requestDto.AuthRequestDto;
import com.kwizera.ibrahim.adminPanel.dto.requestDto.ZipcodeRequestDto;
import com.kwizera.ibrahim.adminPanel.dto.responseDto.AuthorResponseDto;
import com.kwizera.ibrahim.adminPanel.entity.Author;
import com.kwizera.ibrahim.adminPanel.entity.Zipcode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    public AuthorResponseDto addAuthor(AuthRequestDto authRequestDto);
    public List<AuthorResponseDto> getAuthors();
    public AuthorResponseDto getAuthorByID(Long AuthorID);
    public Author getAuthor(Long AuthorID);
    public AuthorResponseDto editAuthor(Long AuthorID, AuthRequestDto authRequestDto);
    public AuthorResponseDto deleteAuthor(Long AuthorID);
    public AuthorResponseDto addZipcodeToAuthor(Long zipcodeID, Long AuthorID);
    public AuthorResponseDto removeZipcodeFromAuthor(Long AuthorID);
}
