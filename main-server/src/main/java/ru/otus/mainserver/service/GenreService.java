package ru.otus.mainserver.service;

import ru.otus.mainserver.dto.AuthorDto;
import ru.otus.mainserver.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> genres();

    GenreDto getById(String id) throws InterruptedException;
}
