package ru.otus.mainserver.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.dto.GenreDto;
import ru.otus.mainserver.feign.BookFeign;
import ru.otus.mainserver.feign.GenreFeign;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreFeign genreFeign;

    @Override
    @HystrixCommand(fallbackMethod = "emptyGenreCollection")
    public List<GenreDto> genres() {
        return genreFeign.genres();
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyGenre")
    public GenreDto getById(String id) throws InterruptedException {
        Thread.sleep(10000);
        return genreFeign.genres(id);
    }

    List<GenreDto> emptyGenreCollection() {
        return List.of(GenreDto.builder().build());
    }

    GenreDto emptyGenre(String id) {
        log.info("отработал HystrixCommand");
        return GenreDto.builder().build();
    }
}
