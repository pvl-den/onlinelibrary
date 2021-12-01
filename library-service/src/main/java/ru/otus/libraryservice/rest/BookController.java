package ru.otus.libraryservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.libraryservice.core.LibraryException;
import ru.otus.libraryservice.core.Response;
import ru.otus.libraryservice.dto.BookDto;
import ru.otus.libraryservice.rest.dto.ParamDto;
import ru.otus.libraryservice.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book")
    public Response<List<BookDto>> books() throws LibraryException {
        return bookService.books();
    }

    @PostMapping("/book/create")
    public Response<BookDto> createBook(@RequestBody ParamDto paramBookDto) throws LibraryException {
        return bookService.createBook(paramBookDto);
    }

    @GetMapping("/book/byId/{id}")
    public Response<BookDto> bookById(@PathVariable("id") final String id) throws LibraryException {
        return bookService.getById(id);
    }

    @GetMapping("/book/byName/{bookName}")
    public Response<BookDto> bookByName(@PathVariable("bookName") final String bookName) throws LibraryException {
        return bookService.getByName(bookName);
    }

}
