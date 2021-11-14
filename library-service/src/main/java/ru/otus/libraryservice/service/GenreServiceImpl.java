package ru.otus.libraryservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.libraryservice.entity.Genre;
import ru.otus.libraryservice.repository.GenreRepository;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public Genre getById(final String id) {
        return genreRepository.findById(id).orElse(null);
    }
}
