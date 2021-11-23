package ru.otus.libraryservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.libraryservice.dto.GenreDto;
import ru.otus.libraryservice.entity.Genre;
import ru.otus.libraryservice.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public GenreDto getById(final String id) {
        final Genre genre = genreRepository.findById(id).orElse(null);
        return GenreDto.toDto(genre);
    }

    @Override
    public List<GenreDto> genres() {
        return genreRepository.findAll().stream().map(GenreDto::toDto).collect(Collectors.toList());
    }
}
