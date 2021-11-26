package ru.otus.mainserver.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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
    public List<GenreDto> genres() {
        log.info("контроллер feign genre");
        return genreService.genres();
    }

    @GetMapping("/genre/{id}")
    public GenreDto genres(@PathVariable("id") final String id) throws InterruptedException {
        return genreService.getById(id);
    }

    @PostMapping("/genre/create")
    @CrossOrigin
    public GenreDto createGenre(@RequestBody ParamDto paramDto){
        return genreService.createGenre(paramDto);
    }

    @PutMapping("/genre/update")
    @CrossOrigin
    public GenreDto updateGenre(@RequestBody ParamDto paramDto){
        return genreService.updateGenre(paramDto);
    }

    @DeleteMapping("/genre/delete/{id}")
    @CrossOrigin
    public Boolean deleteGenre(@PathVariable("id") final String id){
        return genreService.deleteGenre(id);
    }
}
