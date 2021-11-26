package ru.otus.mainserver.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.mainserver.dto.GenreDto;
import ru.otus.mainserver.feign.GenreFeign;
import ru.otus.mainserver.rest.dto.ParamDto;

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
        return genreFeign.getById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyGenre")
    public GenreDto createGenre(ParamDto paramDto) {
        return genreFeign.createGenre(paramDto);
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyGenre")
    public GenreDto updateGenre(ParamDto paramDto) {
        return genreFeign.updateGenre(paramDto);
    }

    @Override
    @HystrixCommand(fallbackMethod = "falseGenre")
    public Boolean deleteGenre(String id) {
        return genreFeign.deleteGenre(id);
    }

    @Override
    public GenreDto getByName(String genreName) {
        return genreFeign.getByName(genreName);
    }

    List<GenreDto> emptyGenreCollection() {
        return List.of(GenreDto.builder().build());
    }

    GenreDto emptyGenre(ParamDto paramDto) {
        return GenreDto.builder().build();
    }

    GenreDto emptyGenre(String id) {
        log.info("проверка HystrixCommand");
        return GenreDto.builder().build();
    }

    Boolean falseGenre(String id) {
        return false;
    }
}
