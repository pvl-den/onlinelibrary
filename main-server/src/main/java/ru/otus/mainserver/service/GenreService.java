package ru.otus.mainserver.service;

import ru.otus.mainserver.dto.GenreDto;
import ru.otus.mainserver.rest.dto.ParamDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> genres();

    GenreDto getById(String id) throws InterruptedException;

    GenreDto createGenre(ParamDto paramDto);

    GenreDto updateGenre(ParamDto paramDto);

    Boolean deleteGenre(String id);
}
