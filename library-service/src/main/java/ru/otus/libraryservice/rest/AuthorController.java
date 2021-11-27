package ru.otus.libraryservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.libraryservice.dto.AuthorDto;
import ru.otus.libraryservice.rest.dto.ParamDto;
import ru.otus.libraryservice.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/author")
    public List<AuthorDto> authors() {
        return authorService.authors();
    }

    @GetMapping("/author/byId{id}")
    public AuthorDto authorById(@PathVariable("id") final String id) {
        return authorService.getById(id);
    }

    @GetMapping("/author/byName/{authorName}")
    public AuthorDto authorByName(@PathVariable("authorName") final String authorName) {
        return authorService.getByName(authorName);
    }

    @PostMapping("/author/create")
    @CrossOrigin
    public AuthorDto createAuthor(@RequestBody ParamDto paramDto){
        return authorService.createAuthor(paramDto);
    }

    @PutMapping("/author/update")
    @CrossOrigin
    public AuthorDto updateAuthor(@RequestBody ParamDto paramDto){
        return authorService.updateAuthor(paramDto);
    }

    @DeleteMapping("/author/delete/{id}")
    @CrossOrigin
    public Boolean deleteAuthor(@PathVariable("id") final String id){
        return authorService.deleteAuthor(id);
    }
}
