package ru.otus.mainserver.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.rest.dto.ParamDto;

import java.util.List;

@Component
@FeignClient("library-service")
public interface BookFeign {

    @GetMapping("/book")
    public List<BookDto>  books();

    @PostMapping("/book/create")
    public BookDto createBook(ParamDto paramBookDto);

    @GetMapping("/book/byName/{bookName}")
    public BookDto getByName(@PathVariable("bookName") String bookName);

    @GetMapping("/book/byId/{id}")
    public BookDto getById(@PathVariable("id") String id);

}
