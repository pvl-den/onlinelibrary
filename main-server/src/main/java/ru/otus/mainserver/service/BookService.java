package ru.otus.mainserver.service;

import ru.otus.mainserver.dto.BookDto;

import java.util.List;

public interface BookService {

    public List<BookDto> getAll();
}
