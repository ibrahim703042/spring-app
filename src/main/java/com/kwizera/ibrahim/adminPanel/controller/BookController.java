package com.kwizera.ibrahim.adminPanel.controller;

import com.kwizera.ibrahim.adminPanel.dto.requestDto.BookRequestDto;
import com.kwizera.ibrahim.adminPanel.dto.responseDto.BookResponseDto;
import com.kwizera.ibrahim.adminPanel.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    //@Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<BookResponseDto> addBook(@RequestBody final BookRequestDto bookRequestDto){
        BookResponseDto bookResponseDto = bookService.addBook(bookRequestDto);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable final long id){
        BookResponseDto bookResponseDto = bookService.getBookByID(id);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BookResponseDto>> getBooks(){
        List<BookResponseDto> books = bookService.getBooks();
        return new ResponseEntity<List<BookResponseDto>>(books, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<BookResponseDto> deleteBook(@PathVariable final  long id){
        BookResponseDto bookResponseDto = bookService.deleteBook(id);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<BookResponseDto> editBook(@PathVariable final long id, @RequestBody final BookRequestDto bookRequestDto){
        BookResponseDto bookResponseDto = bookService.editBook(id, bookRequestDto);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PostMapping("/addCategory/{categoryId}/to/{bookId}")
    public ResponseEntity<BookResponseDto> addCategory(@PathVariable final long bookId, @PathVariable final long categoryId) {
        BookResponseDto bookResponseDto = bookService.addCategoryToBook(bookId,categoryId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PostMapping("/removeCategory/{categoryId}/From/{bookId}")
    public ResponseEntity<BookResponseDto> removeCategory(@PathVariable final long bookId, @PathVariable final long categoryId) {
        BookResponseDto bookResponseDto = bookService.removeCategoryFromBook(bookId,categoryId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PostMapping("/addAuthor/{authorId}/to/{bookId}")
    public ResponseEntity<BookResponseDto> addAuthor(@PathVariable final long bookId, @PathVariable final long authorId) {
        BookResponseDto bookResponseDto = bookService.addAuthorToBook(bookId,authorId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @PostMapping("/removeAuthor/{authorId}/From/{bookId}")
    public ResponseEntity<BookResponseDto> removeAuthor(@PathVariable final long bookId, @PathVariable final long authorId) {
        BookResponseDto bookResponseDto = bookService.deleteAuthorFromBook(bookId,authorId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

}
