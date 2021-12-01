package ru.otus.mainserver.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.otus.mainserver.core.Response;
import ru.otus.mainserver.dto.GenreDto;
import ru.otus.mainserver.rest.dto.ParamDto;

import java.util.List;

@Component
@FeignClient("library-service")
public interface GenreFeign {

    @GetMapping("/genre")
    Response<List<GenreDto>> genres();

    @GetMapping("/genre/byId/{id}")
    Response<GenreDto> getById(@PathVariable("id") final String id);

    @PostMapping("/genre/create")
    Response<GenreDto> createGenre(@RequestBody ParamDto paramDto);

    @PutMapping("/genre/update")
    Response<GenreDto> updateGenre(@RequestBody ParamDto paramDto);

    @DeleteMapping("/genre/delete/{id}")
    Response<Boolean> deleteGenre(@PathVariable("id") String id);

    @GetMapping("/genre/byName/{genreName}")
    Response<GenreDto> getByName(@PathVariable("genreName") String genreName);
}
