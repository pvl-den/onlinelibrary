package ru.otus.mainserver.service;

import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.rest.dto.ParamDto;

import java.util.List;

public interface BookService {

    public List<BookDto> books();

    public BookDto createBook(ParamDto paramBookDto);
}
