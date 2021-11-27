package ru.otus.mainserver.service;

import ru.otus.mainserver.dto.AuthorDto;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.rest.dto.ParamDto;

import java.util.List;

public interface AuthorService {

    public List<AuthorDto> authors();

    AuthorDto getById(String id);

    AuthorDto getByName(String genreName);

    AuthorDto createAuthor(ParamDto paramDto);

    AuthorDto updateAuthor(ParamDto paramDto);

    Boolean deleteAuthor(String id);

}
