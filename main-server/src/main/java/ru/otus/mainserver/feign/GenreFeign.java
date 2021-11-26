package ru.otus.mainserver.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.otus.mainserver.dto.GenreDto;
import ru.otus.mainserver.rest.dto.ParamDto;

import java.util.List;

@Component
@FeignClient("library-service")
public interface GenreFeign {

    @GetMapping("/genre")
    List<GenreDto> genres();

    @GetMapping("/genre/{id}")
    GenreDto genres(@PathVariable("id") final String id);

    @PostMapping("/genre/create")
    GenreDto createGenre(@RequestBody ParamDto paramDto);

    @PutMapping("/genre/update")
    GenreDto updateGenre(@RequestBody ParamDto paramDto);

    @DeleteMapping("/genre/delete/{id}")
    Boolean deleteGenre(@PathVariable("id") String id);
}
