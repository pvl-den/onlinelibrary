package ru.otus.libraryservice.service;

import ru.otus.libraryservice.entity.Author;

import java.util.List;

public interface AuthorService {

    long count();

    Author save(Author author);

    Author getById(String id);

    Author getByName(String authorName);

    List<Author> getAll();

    void deleteById(String id);
}
