package ru.otus.libraryservice.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.otus.libraryservice.core.LibraryException;
import ru.otus.libraryservice.core.Response;
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
    public Response<List<GenreDto>> genres() {
        return genreService.genres();
    }

    @GetMapping("/genre/byId/{id}")
    public Response<GenreDto> genreById(@PathVariable("id") final String id) throws LibraryException {
        return genreService.getById(id);
    }

    @GetMapping("/genre/byName/{genreName}")
    public Response<GenreDto> genreByName(@PathVariable("genreName") final String genreName) throws LibraryException {
        return genreService.getByName(genreName);
    }

    @PostMapping("/genre/create")
    public Response<GenreDto> createGenre(@RequestBody ParamDto paramDto) throws LibraryException {
        return genreService.createGenre(paramDto);
    }

    @PutMapping("/genre/update")
    public Response<GenreDto> updateGenre(@RequestBody ParamDto paramDto) throws LibraryException {
        return genreService.updateGenre(paramDto);
    }

    @DeleteMapping("/genre/delete/{id}")
    public Response<Boolean> deleteGenre(@PathVariable("id") final String id) throws LibraryException {
        return genreService.deleteGenre(id);
    }
}
