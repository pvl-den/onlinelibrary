package ru.otus.libraryservice.service;

import ru.otus.libraryservice.core.LibraryException;
import ru.otus.libraryservice.core.Response;
import ru.otus.libraryservice.dto.BookDto;
import ru.otus.libraryservice.rest.dto.ParamDto;

import java.util.List;

public interface BookService {

    long count();

    Response<BookDto> getById(String id) throws LibraryException;

    Response<BookDto> getByName(String name) throws LibraryException;

    Response<List<BookDto>> books() throws LibraryException;

    Response<Boolean> deleteById(String id) throws LibraryException;

    Response<BookDto> createBook(ParamDto paramBookDto) throws LibraryException;
}
