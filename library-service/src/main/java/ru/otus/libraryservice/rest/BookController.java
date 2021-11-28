package ru.otus.libraryservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.libraryservice.dto.AuthorDto;
import ru.otus.libraryservice.dto.BookDto;
import ru.otus.libraryservice.rest.dto.ParamDto;
import ru.otus.libraryservice.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book")
    public List<BookDto> books() {
        return bookService.books();
    }

    @PostMapping("/book/create")
    public BookDto createBook(@RequestBody ParamDto paramBookDto){
        return bookService.createBook(paramBookDto);
    }

    @GetMapping("/book/byId/{id}")
    public BookDto bookById(@PathVariable("id") final String id) {
        return bookService.getById(id);
    }

    @GetMapping("/book/byName/{bookName}")
    public BookDto bookByName(@PathVariable("bookName") final String bookName) {
        return bookService.getByName(bookName);
    }

}
