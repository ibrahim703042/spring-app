package com.kwizera.ibrahim.adminPanel.service.impl;

import com.kwizera.ibrahim.adminPanel.dto.Mapper;
import com.kwizera.ibrahim.adminPanel.dto.requestDto.BookRequestDto;
import com.kwizera.ibrahim.adminPanel.dto.responseDto.BookResponseDto;
import com.kwizera.ibrahim.adminPanel.entity.Author;
import com.kwizera.ibrahim.adminPanel.entity.Book;
import com.kwizera.ibrahim.adminPanel.entity.Category;
import com.kwizera.ibrahim.adminPanel.repository.BookRepository;
import com.kwizera.ibrahim.adminPanel.service.AuthorService;
import com.kwizera.ibrahim.adminPanel.service.BookService;
import com.kwizera.ibrahim.adminPanel.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    //@Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Transactional
    @Override
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setName(bookRequestDto.getName());
        if(bookRequestDto.getAuthorId().isEmpty()){
            throw new IllegalArgumentException("Book need at least an author");

        }else{
            List<Author> authorList = new ArrayList<>();
            for(Long authorId : bookRequestDto.getAuthorId()){
                Author author = authorService.getAuthor(authorId);
                authorList.add(author);
            }
            book.setAuthors(authorList);
        }
        if(bookRequestDto.getCategoryId() == Long.parseLong(null)){
            throw  new IllegalArgumentException("Book need at least a Category");
        }
        Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
        book.setCategory(category);

        Book book1 = bookRepository.save(book);
        return Mapper.bookToBookResponseDto(book1);
    }

    @Override
    public List<BookResponseDto> getBooks() {
        List<Book> books = StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return Mapper.bookToBookResponseDtos(books);
    }

    @Override
    public BookResponseDto getBookByID(Long bookID) {
        Book book = getBook(bookID);
         return Mapper.bookToBookResponseDto(book);
    }

    @Override
    public Book getBook(Long bookID) {
        return bookRepository.findById(bookID).orElseThrow(() ->
                new IllegalArgumentException("Can not find book with id:" + bookID));
    }

    @Transactional
    @Override
    public BookResponseDto editBook(Long bookID, BookRequestDto bookRequestDto) {
        Book book = getBook(bookID);
        book.setName(bookRequestDto.getName());
        if(bookRequestDto.getAuthorId().isEmpty()){
            List<Author> authorList = new ArrayList<>();
            for(Long authorId : bookRequestDto.getAuthorId()) {
                Author author = authorService.getAuthor(authorId);
                authorList.add(author);
            }
            book.setAuthors(authorList);
        }
        if(bookRequestDto.getCategoryId() != Long.parseLong(null)){
            Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
            book.setCategory(category);
        }
        return Mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto deleteBook(Long bookID) {
        Book book = getBook(bookID);
        bookRepository.delete(book);
        return Mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto addAuthorToBook(Long bookID, Long authorID) {
        Book book = getBook(bookID);
        Author author = authorService.getAuthor(authorID);
        if(author.getBooks().contains(author)){
            throw new IllegalArgumentException("This author is already assigned to this book");
        }
        book.addAuthor(author);
        author.addBook(book);
        return Mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto deleteAuthorFromBook(Long bookID, Long authorID) {
        Book book = getBook(bookID);
        Author author = authorService.getAuthor(authorID);
        if(author.getBooks().contains(book)){
            throw new IllegalArgumentException("Book does not have this author");
        }
        book.removeAuthor(author);
        author.removeBook(book);
        return Mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto addCategoryToBook(Long bookID, Long categoryID) {
        Book book = getBook(bookID);
        Category category = categoryService.getCategory(categoryID);
        if(Objects.nonNull(book.getCategory())){
            throw new IllegalArgumentException("Book already have a category");
        }
        book.setCategory(category);
        category.addBook(book);
        return Mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto removeCategoryFromBook(Long bookID, Long categoryID) {
        Book book = getBook(bookID);
        Category category = categoryService.getCategory(categoryID);
        if(!(Objects.nonNull(book.getCategory()))){
            throw new IllegalArgumentException("Book does not have a category to delete");
        }
        book.setCategory(null);
        category.removeBook(book);
        return Mapper.bookToBookResponseDto(book);
    }
}
