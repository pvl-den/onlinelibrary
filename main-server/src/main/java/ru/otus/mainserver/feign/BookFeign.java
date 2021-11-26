package ru.otus.mainserver.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.rest.dto.ParamBookDto;

import java.util.List;

@Component
@FeignClient("library-service")
public interface BookFeign {

    @GetMapping("/book")
    public List<BookDto>  books();

    @PostMapping("/edit/book")
    public BookDto createBook(ParamBookDto paramBookDto);

}
