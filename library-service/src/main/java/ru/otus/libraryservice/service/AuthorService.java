package ru.otus.libraryservice.service;

import ru.otus.libraryservice.dto.AuthorDto;
import ru.otus.libraryservice.entity.Author;

import java.util.List;

public interface AuthorService {

    long count();

    AuthorDto save(AuthorDto author);

    AuthorDto getById(String id);

    AuthorDto getByName(String authorName);

    List<AuthorDto> authors();

    void deleteById(String id);
}
