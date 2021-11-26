package ru.otus.libraryservice.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.otus.libraryservice.dto.GenreDto;
import ru.otus.libraryservice.rest.dto.ParamDto;
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

    @PostMapping("/genre/create")
    public GenreDto createGenre(@RequestBody ParamDto paramDto){
        return genreService.createGenre(paramDto);
    }

    @PutMapping("/genre/update")
    public GenreDto updateGenre(@RequestBody ParamDto paramDto){
        return genreService.updateGenre(paramDto);
    }

    @DeleteMapping("/genre/delete/{id}")
    public Boolean deleteGenre(@PathVariable("id") final String id){
        return genreService.deleteGenre(id);
    }
}
