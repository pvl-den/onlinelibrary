package ru.otus.mainserver.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.mainserver.dto.AuthorDto;
import ru.otus.mainserver.dto.BookDto;

import java.util.List;

@Component
@FeignClient("library-service")
@RequestMapping(value = "/", produces = {"application/json; charset=UTF-8"})
public interface AuthorFeign {

    @GetMapping("/author")
    List<AuthorDto>  authors();
}
