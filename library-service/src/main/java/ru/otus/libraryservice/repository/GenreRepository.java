package ru.otus.libraryservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.libraryservice.entity.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

}
