package com.kwizera.ibrahim.adminPanel.dto;

import com.kwizera.ibrahim.adminPanel.dto.responseDto.AuthorResponseDto;
import com.kwizera.ibrahim.adminPanel.dto.responseDto.BookResponseDto;
import com.kwizera.ibrahim.adminPanel.dto.responseDto.CategoryResponseDto;
import com.kwizera.ibrahim.adminPanel.entity.Author;
import com.kwizera.ibrahim.adminPanel.entity.Book;
import com.kwizera.ibrahim.adminPanel.entity.Category;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class Mapper {
    public static BookResponseDto bookToBookResponseDto (Book book){
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setCategoryName(book.getCategory().getName());
        List<String> names = new ArrayList<>();
        List<Author> authors = new ArrayList<>();

        for(Author author : authors){
            author.getName();
        }

        bookResponseDto.setAuthorNames(names);

        return bookResponseDto;
    }

    public static  List<BookResponseDto> bookToBookResponseDtos(List<Book> books){
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();
        for (Book book : books){
            bookResponseDtos.add(bookToBookResponseDto(book));
        }
        return bookResponseDtos;
    }
    public static CategoryResponseDto categoryToCategoryResponseDto(Category category){
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        category.setName(category.getName());
        List<String> names = new ArrayList<>();
        List<Book> books = category.getBooks();

        for(Book book : books){
            book.getName();
        }

        categoryResponseDto.setBookNames(names);

        return categoryResponseDto;
    }

    public static  List<CategoryResponseDto> categoryToCategoryResponseDtos(List<Category> categories){
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
        for (Category category : categories){
            categoryResponseDtos.add(categoryToCategoryResponseDto(category));
        }
        return categoryResponseDtos;
    }


    public static AuthorResponseDto authorToAuthorResponseDto(Author author){
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(author.getId());
        authorResponseDto.setName(author.getName());
        List<String> names = new ArrayList<>();
        List<Book> books = author.getBooks();

        for(Book book : books){
            book.getName();
        }

        authorResponseDto.setBookName(names);

        return authorResponseDto;
    }

    public static  List<AuthorResponseDto> authorToAuthorResponseDtos(List<Author> authors){
        List<AuthorResponseDto> authorResponseDtos = new ArrayList<>();
        for (Author author : authors){
            authorResponseDtos.add(authorToAuthorResponseDto(author));
        }
        return authorResponseDtos;
    }
}

