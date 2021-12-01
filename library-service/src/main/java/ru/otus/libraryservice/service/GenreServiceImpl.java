package ru.otus.libraryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.libraryservice.core.LibraryException;
import ru.otus.libraryservice.core.Response;
import ru.otus.libraryservice.dto.GenreDto;
import ru.otus.libraryservice.entity.Genre;
import ru.otus.libraryservice.repository.GenreRepository;
import ru.otus.libraryservice.rest.dto.ParamDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public Response<GenreDto> getById(final String id) throws LibraryException {
        final Genre genre = genreRepository.findById(id).orElse(null);

        if (genre == null)
            throw new LibraryException("ошибка поиска жанра с id: " + id);

        return new Response<>(GenreDto.toDto(genre));
    }

    @Override
    @Transactional(readOnly = true)
    public Response<GenreDto> getByName(String genreName) throws LibraryException {
        final Genre genre = genreRepository.findByName(genreName);

        if (genre == null)
            throw new LibraryException("ошибка поиска жанра с именем: " + genreName);

        return new Response<>(GenreDto.toDto(genre));
    }

    @Override
    public Response<List<GenreDto>> genres() {
        final List<GenreDto> genres = genreRepository.findAll().stream().map(GenreDto::toDto).collect(Collectors.toList());
        return new Response<>(genres);
    }

    @Override
    @Transactional
    public Response<GenreDto> createGenre(ParamDto paramDto) throws LibraryException {
        if (StringUtils.isBlank(paramDto.getGenreName())) {
            throw new LibraryException("Ошибка параметров");
        }

        log.info("создание жанра: {}", paramDto.getGenreName());

        try {
            final Genre genre = Genre.builder().name(paramDto.getGenreName()).build();
            final Genre savedGenre = genreRepository.save(genre);
            return new Response(GenreDto.toDto(savedGenre));
        } catch (Exception e) {
            log.error("Ошибка создания жанра: {}", paramDto.getGenreName());
            throw new LibraryException("Ошибка создания жанра: " + paramDto.getGenreName());
        }

    }

    @Override
    @Transactional
    public Response<GenreDto> updateGenre(ParamDto paramDto) throws LibraryException {

        if (StringUtils.isBlank(paramDto.getGenreName()) || StringUtils.isBlank(paramDto.getId())) {
            throw new LibraryException("Ошибка параметров");
        }

        log.info("обновление жанра: {}", paramDto.getGenreName());

        try {
            final Genre genre = Genre.builder().id(paramDto.getId()).name(paramDto.getGenreName()).build();
            final Genre savedGenre = genreRepository.save(genre);
            return new Response(GenreDto.toDto(savedGenre));
        } catch (Exception e) {
            log.error("Ошибка обновления жанра: {} ", paramDto.getGenreName());
            throw new LibraryException("Ошибка обновления жанра: " + paramDto.getGenreName());
        }
    }

    @Override
    @Transactional
    public Response<Boolean> deleteGenre(String id) throws LibraryException {
        log.info("удаление жанра с id: {}", id);
        try {
            genreRepository.deleteById(id);
            return new Response<>(true);
        } catch (Exception e) {
            log.error("Ошибка удаления жанра с id: {} ", id);
            throw new LibraryException("ошибка удаления жанра с id: " + id);
        }
    }
}
