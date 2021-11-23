package ru.otus.mainserver.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.dto.GenreDto;

import java.util.List;

@Component
@FeignClient("library-service")
public interface GenreFeign {

    @GetMapping("/genre")
    List<GenreDto>  genres();

}
