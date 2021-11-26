package ru.otus.mainserver.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.dto.GenreDto;
import ru.otus.mainserver.service.BookService;
import ru.otus.mainserver.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genre")
    @CrossOrigin
    public List<GenreDto> genres() {
        log.info("контроллер feign genre");
        return genreService.genres();
    }

    @GetMapping("/genre/{id}")
    public GenreDto genres(@PathVariable("id") final String id) throws InterruptedException {
        return genreService.getById(id);
    }
}
