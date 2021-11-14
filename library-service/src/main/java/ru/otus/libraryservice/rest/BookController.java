package ru.otus.libraryservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.libraryservice.dto.BookDto;
import ru.otus.libraryservice.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book")
    public String allBooks() {
        final List<BookDto> books = bookService.getAll().stream().map(BookDto::toDto).collect(Collectors.toList());
        return "books";
    }

}
