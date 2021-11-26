package ru.otus.libraryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public GenreDto getById(final String id) {
        final Genre genre = genreRepository.findById(id).orElse(null);
        return GenreDto.toDto(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public GenreDto getByName(String genreName) {
        final Genre genre = genreRepository.findByName(genreName);
        return GenreDto.toDto(genre);
    }

    @Override
    public List<GenreDto> genres() {
        return genreRepository.findAll().stream().map(GenreDto::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public GenreDto createGenre(ParamDto paramDto) {
        if (StringUtils.isBlank(paramDto.getGenreName())) {
            throw new IllegalArgumentException("Ошибка параметров");
        }

        log.info("создание жанра: {}", paramDto.getGenreName());

        try {
            final Genre genre = Genre.builder().name(paramDto.getGenreName()).build();
            final Genre savedGenre = genreRepository.save(genre);
            return GenreDto.toDto(savedGenre);
        } catch (Exception e) {
            log.error("Ошибка создания жанра: {}", paramDto.getGenreName());
            return null;
        }

    }

    @Override
    @Transactional
    public GenreDto updateGenre(ParamDto paramDto) {

        if (StringUtils.isBlank(paramDto.getGenreName()) || StringUtils.isBlank(paramDto.getId())) {
            throw new IllegalArgumentException("Ошибка параметров");
        }

        log.info("обновление жанра: {}", paramDto.getGenreName());

        try {
            final Genre genre = Genre.builder().id(paramDto.getId()).name(paramDto.getGenreName()).build();
            final Genre savedGenre = genreRepository.save(genre);
            return GenreDto.toDto(savedGenre);
        } catch (Exception e) {
            log.error("Ошибка обновления жанра: {} ", paramDto.getGenreName());
            return null;
        }
    }

    @Override
    @Transactional
    public Boolean deleteGenre(String id) {
        log.info("удаление жанра с id: {}", id);
        try {
            genreRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Ошибка удаления жанра с id: {} ", id);
            return false;
        }
    }
}
