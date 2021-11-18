package ru.otus.mainserver.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.mainserver.dto.BookDto;

import java.util.List;

@FeignClient("library-service")
public interface BookFeign {

    @GetMapping("/book")
    List<BookDto>  getAll();
}
