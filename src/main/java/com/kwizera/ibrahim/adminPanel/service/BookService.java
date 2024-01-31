package com.kwizera.ibrahim.adminPanel.service;

import com.kwizera.ibrahim.adminPanel.dto.requestDto.BookRequestDto;
import com.kwizera.ibrahim.adminPanel.dto.responseDto.BookResponseDto;
import com.kwizera.ibrahim.adminPanel.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public BookResponseDto addBook(BookRequestDto bookRequestDto);
    public List<BookResponseDto> getBooks();
    public BookResponseDto getBookByID(Long bookID);
    public Book getBook(Long bookID);
    public BookResponseDto editBook(Long bookID, BookRequestDto bookRequestDto);
    public BookResponseDto deleteBook(Long bookID);
    public BookResponseDto addAuthorToBook(Long bookID, Long authorID);
    public BookResponseDto deleteAuthorFromBook(Long bookID, Long authorID);
    public BookResponseDto addCategoryToBook(Long bookID, Long categoryID);
    public BookResponseDto removeCategoryFromBook(Long bookID, Long categoryID);
}
