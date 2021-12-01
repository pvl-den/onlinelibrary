package ru.otus.libraryservice.service;

import ru.otus.libraryservice.core.LibraryException;
import ru.otus.libraryservice.core.Response;
import ru.otus.libraryservice.dto.AuthorDto;
import ru.otus.libraryservice.rest.dto.ParamDto;

import java.util.List;

public interface AuthorService {

    Response<AuthorDto> getById(String id) throws LibraryException;

    Response<AuthorDto> getByName(String authorName) throws LibraryException;

    Response<List<AuthorDto>> authors() throws LibraryException;

    Response<AuthorDto> createAuthor(ParamDto paramDto) throws LibraryException;

    Response<AuthorDto> updateAuthor(ParamDto paramDto) throws LibraryException;

    Response<Boolean> deleteAuthor(String id) throws LibraryException;
}
