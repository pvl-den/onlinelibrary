package ru.otus.libraryservice.service;

import ru.otus.libraryservice.dto.BookDto;
import ru.otus.libraryservice.rest.dto.ParamDto;

import java.util.List;

public interface BookService {

    long count();

    BookDto getById(String id);

    BookDto getByName(String name);

    List<BookDto> books();

    void deleteById(String id);

    BookDto createBook(ParamDto paramBookDto);
}
