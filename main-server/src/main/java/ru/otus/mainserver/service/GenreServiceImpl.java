package ru.otus.mainserver.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.dto.GenreDto;
import ru.otus.mainserver.feign.BookFeign;
import ru.otus.mainserver.feign.GenreFeign;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreFeign genreFeign;

    @Override
    @HystrixCommand(fallbackMethod = "emptyGenreCollection")
    public List<GenreDto> genres() {
        return genreFeign.genres();
    }

    List<GenreDto> emptyGenreCollection() {
        return List.of(GenreDto.builder().build());
    }
}
