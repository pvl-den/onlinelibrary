package ru.otus.mainserver.rest;

import org.springframework.web.bind.annotation.*;
import ru.otus.mainserver.dto.AuthorDto;
import ru.otus.mainserver.dto.GenreDto;
import ru.otus.mainserver.rest.dto.ParamDto;
import ru.otus.mainserver.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @CrossOrigin
    @GetMapping("/author")
    public List<AuthorDto> authors() {
        return authorService.authors();
    }

    @CrossOrigin
    @GetMapping("/author/byId{id}")
    public AuthorDto authorById(@PathVariable("id") final String id) {
        return authorService.getById(id);
    }

    @CrossOrigin
    @GetMapping("/author/byName/{authorName}")
    public AuthorDto authorByName(@PathVariable("authorName") final String authorName) {
        return authorService.getByName(authorName);
    }

    @CrossOrigin
    @PostMapping("/author/create")
    public AuthorDto createAuthor(@RequestBody ParamDto paramDto){
        return authorService.createAuthor(paramDto);
    }

    @CrossOrigin
    @PutMapping("/author/update")
    public AuthorDto updateAuthor(@RequestBody ParamDto paramDto){
        return authorService.updateAuthor(paramDto);
    }

    @CrossOrigin
    @DeleteMapping("/author/delete/{id}")
    public Boolean deleteAuthor(@PathVariable("id") final String id){
        return authorService.deleteAuthor(id);
    }
}
