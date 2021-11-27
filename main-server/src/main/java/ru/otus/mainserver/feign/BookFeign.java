package ru.otus.mainserver.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
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

}
