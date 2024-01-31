package com.kwizera.ibrahim.adminPanel.service.impl;

import com.kwizera.ibrahim.adminPanel.dto.Mapper;
import com.kwizera.ibrahim.adminPanel.dto.requestDto.AuthRequestDto;
import com.kwizera.ibrahim.adminPanel.dto.responseDto.AuthorResponseDto;
import com.kwizera.ibrahim.adminPanel.entity.Author;
import com.kwizera.ibrahim.adminPanel.entity.Zipcode;
import com.kwizera.ibrahim.adminPanel.repository.AuthorRepository;
import com.kwizera.ibrahim.adminPanel.service.AuthorService;
import com.kwizera.ibrahim.adminPanel.service.ZipcodeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ZipcodeService zipcodeService;

    //@Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ZipcodeService zipcodeService) {
        this.authorRepository = authorRepository;
        this.zipcodeService = zipcodeService;
    }

    @Override
    public AuthorResponseDto addAuthor(AuthRequestDto authRequestDto) {
        Author author = new Author();
        author.setName(authRequestDto.getName());
        if(authRequestDto.getZipcodeId() == Long.parseLong(null)){
            throw new IllegalArgumentException("Author need a zipcode");
        }

        Zipcode zipcode = zipcodeService.getZipcode(authRequestDto.getZipcodeId());
        author.setZipcode(zipcode);
        authorRepository.save(author);
        return Mapper.authorToAuthorResponseDto(author);
    }

    @Override
    public List<AuthorResponseDto> getAuthors() {
        List<Author> authors = StreamSupport
                .stream(authorRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());

        return Mapper.authorToAuthorResponseDtos(authors);
    }

    @Override
    public AuthorResponseDto getAuthorByID(Long authorID) {
        return Mapper.authorToAuthorResponseDto(getAuthor(authorID));
    }
    @Override
    public Author getAuthor(Long authorID) {
        return authorRepository.findById(authorID).orElseThrow(()
                ->new IllegalArgumentException("Author with id: " + authorID + " could not be found"));
    }

    @Transactional
    @Override
    public AuthorResponseDto editAuthor(Long authorID, AuthRequestDto authRequestDto) {
        Author author = getAuthor(authorID);
        author.setName(authRequestDto.getName());
        if(authRequestDto.getZipcodeId() != Long.parseLong(null)){
            Zipcode zipcode = zipcodeService.getZipcode(authRequestDto.getZipcodeId());
            author.setZipcode(zipcode);
        }
        return Mapper.authorToAuthorResponseDto(author);
    }

    @Override
    public AuthorResponseDto deleteAuthor(Long authorID) {
    Author author = getAuthor(authorID);
        authorRepository.delete(author);
        return Mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto addZipcodeToAuthor(Long zipcodeID, Long authorID) {
        Author author = getAuthor(authorID);
        Zipcode zipcode = zipcodeService.getZipcode(zipcodeID);
        if(Objects.nonNull(author.getZipcode())){
            throw new RuntimeException("Author already have a zipcode");
        }
        author.setZipcode(zipcode);
        return Mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto removeZipcodeFromAuthor(Long authorID) {
        Author author = getAuthor(authorID);
        author.setZipcode(null);
        return Mapper.authorToAuthorResponseDto(author);
    }
}
