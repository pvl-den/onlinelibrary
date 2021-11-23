package ru.otus.libraryservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.libraryservice.dto.GenreDto;
import ru.otus.libraryservice.entity.Genre;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, String> {

    Genre findByName(String name);
}
