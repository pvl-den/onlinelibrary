package ru.otus.libraryservice.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.libraryservice.dto.AuthorDto;
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

}
