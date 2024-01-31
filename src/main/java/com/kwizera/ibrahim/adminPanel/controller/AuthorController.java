package com.kwizera.ibrahim.adminPanel.controller;

import com.kwizera.ibrahim.adminPanel.dto.requestDto.AuthRequestDto;
import com.kwizera.ibrahim.adminPanel.dto.responseDto.AuthorResponseDto;
import com.kwizera.ibrahim.adminPanel.service.AuthorService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    //@Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/add")
    public ResponseEntity<AuthorResponseDto> addAuthor(@RequestBody final AuthRequestDto authRequestDto){
        AuthorResponseDto author = authorService.addAuthor(authRequestDto);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthor(@PathVariable final long id){
        AuthorResponseDto author = authorService.getAuthorByID(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AuthorResponseDto>> getAuthors(){
        List<AuthorResponseDto> authors = authorService.getAuthors();
        return new ResponseEntity<List<AuthorResponseDto>>(authors, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<AuthorResponseDto> deleteAuthor(@PathVariable final  long id){
        AuthorResponseDto author = authorService.deleteAuthor(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<AuthorResponseDto> editAuthor(@PathVariable final long id, @RequestBody final AuthRequestDto authRequestDto){
        AuthorResponseDto author = authorService.editAuthor(id, authRequestDto);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping("/addZipcode/{zipcodeId}/to/{authorId}")
    public ResponseEntity<AuthorResponseDto> addZipcode(@PathVariable final long zipcodeId, @PathVariable final long authorId) {
        AuthorResponseDto author = authorService.addZipcodeToAuthor(zipcodeId, authorId);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping("/removeZipcode/{authorId}")
    public ResponseEntity<AuthorResponseDto> deleteZipcode(@PathVariable final long authorId) {
        AuthorResponseDto author = authorService.removeZipcodeFromAuthor(authorId);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }
}
