package ru.otus.libraryservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.libraryservice.core.LibraryException;
import ru.otus.libraryservice.core.Response;
import ru.otus.libraryservice.dto.AuthorDto;
import ru.otus.libraryservice.rest.dto.ParamDto;
import ru.otus.libraryservice.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/author")
    public Response<List<AuthorDto>> authors() throws LibraryException {
        return authorService.authors();
    }

    @GetMapping("/author/byId/{id}")
    public Response<AuthorDto> authorById(@PathVariable("id") final String id) throws LibraryException {
        return authorService.getById(id);
    }

    @GetMapping("/author/byName/{authorName}")
    public Response<AuthorDto> authorByName(@PathVariable("authorName") final String authorName) throws LibraryException {
        return authorService.getByName(authorName);
    }

    @PostMapping("/author/create")
    @CrossOrigin
    public Response<AuthorDto> createAuthor(@RequestBody ParamDto paramDto) throws LibraryException {
        return authorService.createAuthor(paramDto);
    }

    @PutMapping("/author/update")
    @CrossOrigin
    public Response<AuthorDto> updateAuthor(@RequestBody ParamDto paramDto) throws LibraryException {
        return authorService.updateAuthor(paramDto);
    }

    @DeleteMapping("/author/delete/{id}")
    @CrossOrigin
    public Response<Boolean> deleteAuthor(@PathVariable("id") final String id) throws LibraryException {
        return authorService.deleteAuthor(id);
    }
}
