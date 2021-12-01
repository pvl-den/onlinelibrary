package ru.otus.mainserver.service;

import ru.otus.mainserver.core.Response;
import ru.otus.mainserver.dto.AuthorDto;
import ru.otus.mainserver.rest.dto.ParamDto;

import java.util.List;

public interface AuthorService {

    Response<List<AuthorDto>> authors();

    Response<AuthorDto> getById(String id) throws InterruptedException;

    Response<AuthorDto> getByName(String genreName);

    Response<AuthorDto> createAuthor(ParamDto paramDto);

    Response<AuthorDto> updateAuthor(ParamDto paramDto);

    Response<Boolean> deleteAuthor(String id);

}
