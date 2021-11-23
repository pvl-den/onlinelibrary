package ru.otus.libraryservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.libraryservice.dto.GenreDto;
import ru.otus.libraryservice.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genre")
    public List<GenreDto> genres() {
        return genreService.genres();
    }

}
