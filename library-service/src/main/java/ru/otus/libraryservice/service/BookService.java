package ru.otus.libraryservice.service;

import ru.otus.libraryservice.entity.Book;

import java.util.List;

public interface BookService {

    long count();

    Book save(Book book);

    Book getById(String id);

    Book getByName(String name);

    List<Book> getAll();

    void deleteById(String id);

    Book createBook(String name, String authorId, String genreId);
}
