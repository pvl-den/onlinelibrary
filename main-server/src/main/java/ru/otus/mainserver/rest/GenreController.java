package ru.otus.mainserver.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.dto.GenreDto;
import ru.otus.mainserver.service.BookService;
import ru.otus.mainserver.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genre")
    @CrossOrigin
    public List<GenreDto> genres() {
        return genreService.genres();
    }
}
