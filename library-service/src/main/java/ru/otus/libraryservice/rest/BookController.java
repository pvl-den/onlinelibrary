package ru.otus.libraryservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
}
