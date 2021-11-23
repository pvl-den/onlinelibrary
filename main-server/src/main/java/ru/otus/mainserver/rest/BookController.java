package ru.otus.mainserver.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.rest.dto.ParamBookDto;
import ru.otus.mainserver.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book")
    @CrossOrigin
    public List<BookDto> books() {
        return bookService.books();
    }

    @PostMapping("/book")
    @CrossOrigin
    public BookDto createBook(@RequestBody ParamBookDto paramBookDto){
        return bookService.createBook(paramBookDto);
    }
}
