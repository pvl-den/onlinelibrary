package ru.otus.mainserver.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.mainserver.core.Response;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.rest.dto.ParamDto;
import ru.otus.mainserver.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book")
    @CrossOrigin
    public Response<List<BookDto>> books() {
        return bookService.books();
    }

    @PostMapping("/book/create")
    @CrossOrigin
    public Response<BookDto> createBook(@RequestBody ParamDto paramBookDto){
        return bookService.createBook(paramBookDto);
    }

    @CrossOrigin
    @GetMapping("/book/byId/{id}")
    public Response<BookDto> bookById(@PathVariable("id") final String id) {
        return bookService.getById(id);
    }

    @CrossOrigin
    @GetMapping("/book/byName/{bookName}")
    public Response<BookDto> bookByName(@PathVariable("bookName") final String bookName) {
        return bookService.getByName(bookName);
    }

}
