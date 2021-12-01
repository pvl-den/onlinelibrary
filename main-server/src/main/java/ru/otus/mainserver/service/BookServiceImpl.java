package ru.otus.mainserver.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.mainserver.core.Response;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.feign.BookFeign;
import ru.otus.mainserver.rest.dto.ParamDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookFeign bookFeign;

    @Override
    @HystrixCommand(fallbackMethod = "emptyBookCollection")
    public Response<List<BookDto>> books() {
        return bookFeign.books();
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyBook")
    public Response<BookDto> createBook(ParamDto paramBookDto) {
        return bookFeign.createBook(paramBookDto);
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyBook")
    public Response<BookDto> getById(String id) {
        return bookFeign.getById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyBook")
    public Response<BookDto> getByName(String bookName) {
        return bookFeign.getByName(bookName);
    }

    Response<BookDto> emptyBook(ParamDto paramBookDto) {
        return new Response("Привет от fallbackMethod");
    }

    Response<BookDto> emptyBook(String bookName) {
        return new Response("Привет от fallbackMethod");
    }

    Response<List<BookDto>> emptyBookCollection() {
        return new Response("Привет от fallbackMethod");
    }
}
