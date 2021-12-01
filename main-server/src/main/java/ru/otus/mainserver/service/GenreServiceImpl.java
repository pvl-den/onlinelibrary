package ru.otus.mainserver.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.mainserver.core.Response;
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
    public Response<List<GenreDto>> genres() {
        return genreFeign.genres();
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyGenre")
    public Response<GenreDto> getById(String id) throws InterruptedException {
        Thread.sleep(10000);
        return genreFeign.getById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyGenre")
    public Response<GenreDto> createGenre(ParamDto paramDto) {
        return genreFeign.createGenre(paramDto);
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyGenre")
    public Response<GenreDto> updateGenre(ParamDto paramDto) {
        return genreFeign.updateGenre(paramDto);
    }

    @Override
    @HystrixCommand(fallbackMethod = "falseGenre")
    public Response<Boolean> deleteGenre(String id) {
        return genreFeign.deleteGenre(id);
    }

    @Override
    public Response<GenreDto> getByName(String genreName) {
        return genreFeign.getByName(genreName);
    }

    Response<List<GenreDto>> emptyGenreCollection() {
        return new Response("Привет от fallbackMethod");
    }

    Response<GenreDto> emptyGenre(ParamDto paramDto) {
        return new Response("Привет от fallbackMethod");
    }

    Response<GenreDto> emptyGenre(String id) {
        log.error("проверка HystrixCommand");
        return new Response("Привет от fallbackMethod");
    }

    Response<Boolean> falseGenre(String id) {
        return new Response("Привет от fallbackMethod");
    }
}
