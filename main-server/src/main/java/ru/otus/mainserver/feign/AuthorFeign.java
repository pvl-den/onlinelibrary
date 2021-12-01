package ru.otus.mainserver.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.otus.mainserver.core.Response;
import ru.otus.mainserver.dto.AuthorDto;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.rest.dto.ParamDto;

import java.util.List;

@Component
@FeignClient("library-service")
@RequestMapping(value = "/", produces = {"application/json; charset=UTF-8"})
public interface AuthorFeign {

    @GetMapping("/author")
    Response<List<AuthorDto>> authors();

    @GetMapping("/author/byId/{id}")
    Response<AuthorDto> getById(@PathVariable("id") final String id);

    @GetMapping("/author/byName/{authorName}")
    Response<AuthorDto> getByName(@PathVariable("authorName") final String authorName);

    @PostMapping("/author/create")
    Response<AuthorDto> createAuthor(@RequestBody ParamDto paramDto);

    @PutMapping("/author/update")
    Response<AuthorDto> updateAuthor(@RequestBody ParamDto paramDto);

    @DeleteMapping("/author/delete/{id}")
    Response<Boolean> deleteAuthor(@PathVariable("id") final String id);

}
