package ru.otus.libraryservice.service;

import ru.otus.libraryservice.dto.GenreDto;
import ru.otus.libraryservice.entity.Genre;
import ru.otus.libraryservice.rest.dto.ParamDto;

import java.util.List;

public interface GenreService {

    GenreDto getById(String id);

    GenreDto getByName(String genreName);

    List<GenreDto> genres();

    GenreDto createGenre(ParamDto paramDto);

    GenreDto updateGenre(ParamDto paramDto);

    Boolean deleteGenre(String id);

}
