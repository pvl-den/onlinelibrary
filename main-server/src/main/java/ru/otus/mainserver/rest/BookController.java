package ru.otus.mainserver.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book")
    public String allBooks() {
        final List<BookDto> books = bookService.getAll();
        return "books";
    }


}
