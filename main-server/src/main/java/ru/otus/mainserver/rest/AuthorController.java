package ru.otus.mainserver.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.mainserver.dto.AuthorDto;
import ru.otus.mainserver.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author")
    @CrossOrigin
    public List<AuthorDto> authors() {
        return authorService.authors();
    }
}
