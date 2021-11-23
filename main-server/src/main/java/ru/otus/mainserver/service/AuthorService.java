package ru.otus.mainserver.service;

import ru.otus.mainserver.dto.AuthorDto;
import ru.otus.mainserver.dto.BookDto;

import java.util.List;

public interface AuthorService {

    public List<AuthorDto> authors();
}
