package ru.otus.libraryservice.service;

import ru.otus.libraryservice.dto.AuthorDto;
import ru.otus.libraryservice.entity.Author;
import ru.otus.libraryservice.rest.dto.ParamDto;

import java.util.List;

public interface AuthorService {

    AuthorDto getById(String id);

    AuthorDto getByName(String authorName);

    List<AuthorDto> authors();

    AuthorDto createAuthor(ParamDto paramDto);

    AuthorDto updateAuthor(ParamDto paramDto);

    Boolean deleteAuthor(String id);
}
