package ru.otus.mainserver.service;

import ru.otus.mainserver.core.Response;
import ru.otus.mainserver.dto.GenreDto;
import ru.otus.mainserver.rest.dto.ParamDto;

import java.util.List;

public interface GenreService {

    Response<List<GenreDto>> genres();

    Response<GenreDto> getById(String id) throws InterruptedException;

    Response<GenreDto> createGenre(ParamDto paramDto);

    Response<GenreDto> updateGenre(ParamDto paramDto);

    Response<Boolean> deleteGenre(String id);

    Response<GenreDto> getByName(String genreName);
}
