package ru.otus.libraryservice.service;

import ru.otus.libraryservice.core.LibraryException;
import ru.otus.libraryservice.core.Response;
import ru.otus.libraryservice.dto.GenreDto;
import ru.otus.libraryservice.rest.dto.ParamDto;

import java.util.List;

public interface GenreService {

    Response<GenreDto> getById(String id) throws LibraryException;

    Response<GenreDto> getByName(String genreName) throws LibraryException;

    Response<List<GenreDto>> genres();

    Response<GenreDto> createGenre(ParamDto paramDto) throws LibraryException;

    Response<GenreDto> updateGenre(ParamDto paramDto) throws LibraryException;

    Response<Boolean> deleteGenre(String id) throws LibraryException;

}
