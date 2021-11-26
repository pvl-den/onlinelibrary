package ru.otus.libraryservice.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.libraryservice.dto.GenreDto;
import ru.otus.libraryservice.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genre")
    public List<GenreDto> genres() {
        return genreService.genres();
    }

    @GetMapping("/genre/{id}")
    public GenreDto genres(@PathVariable("id") final String id) {
        return genreService.getById(id);
    }

}
