package ru.otus.mainserver.service;

import ru.otus.mainserver.core.Response;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.rest.dto.ParamDto;

import java.util.List;

public interface BookService {

    public Response<List<BookDto>> books();

    public Response<BookDto> createBook(ParamDto paramBookDto);

    public Response<BookDto> getById(String id);

    public Response<BookDto> getByName(String bookName);
}
