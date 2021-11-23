package ru.otus.libraryservice.service;

import ru.otus.libraryservice.dto.GenreDto;
import ru.otus.libraryservice.entity.Genre;

import java.util.List;

public interface GenreService {

    GenreDto getById(String id);

    List<GenreDto> genres();

}
