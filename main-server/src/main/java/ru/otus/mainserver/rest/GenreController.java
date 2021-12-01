package ru.otus.mainserver.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.otus.mainserver.core.Response;
import ru.otus.mainserver.dto.GenreDto;
import ru.otus.mainserver.rest.dto.ParamDto;
import ru.otus.mainserver.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genre")
    @CrossOrigin
    public Response<List<GenreDto>> genres() {
        return genreService.genres();
    }

    @GetMapping("/genre/byId/{id}")
    public Response<GenreDto> genreById(@PathVariable("id") final String id) throws InterruptedException {
        return genreService.getById(id);
    }

    @GetMapping("/genre/byName/{genreName}")
    public Response<GenreDto> genreByName(@PathVariable("genreName") final String genreName) {
        return genreService.getByName(genreName);
    }

    @PostMapping("/genre/create")
    @CrossOrigin
    public Response<GenreDto> createGenre(@RequestBody ParamDto paramDto){
        return genreService.createGenre(paramDto);
    }

    @PutMapping("/genre/update")
    @CrossOrigin
    public Response<GenreDto> updateGenre(@RequestBody ParamDto paramDto){
        return genreService.updateGenre(paramDto);
    }

    @DeleteMapping("/genre/delete/{id}")
    @CrossOrigin
    public Response<Boolean> deleteGenre(@PathVariable("id") final String id){
        return genreService.deleteGenre(id);
    }
}
