package ru.otus.libraryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.libraryservice.dto.AuthorDto;
import ru.otus.libraryservice.dto.GenreDto;
import ru.otus.libraryservice.entity.Author;
import ru.otus.libraryservice.entity.Genre;
import ru.otus.libraryservice.repository.AuthorRepository;
import ru.otus.libraryservice.rest.dto.ParamDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public AuthorDto getById(final String id) {
        final Author author = authorRepository.findById(id).orElse(null);

        return AuthorDto.toDto(author);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDto getByName(String authorName) {
        final Author author = authorRepository.findByName(authorName);

        return AuthorDto.toDto(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> authors() {
        return authorRepository.findAll().stream().map(AuthorDto::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AuthorDto createAuthor(ParamDto paramDto) {

        if (StringUtils.isBlank(paramDto.getAuthorName())) {
            throw new IllegalArgumentException("Ошибка параметров");
        }

        log.info("создание автора: {}", paramDto.getAuthorName());

        try {
            final Author author = Author.builder().name(paramDto.getAuthorName()).build();
            final Author savedAuthor = authorRepository.save(author);
            return AuthorDto.toDto(savedAuthor);
        } catch (Exception e) {
            log.error("Ошибка создания автора: {}", paramDto.getAuthorName());
            return null;
        }
    }

    @Override
    @Transactional
    public AuthorDto updateAuthor(ParamDto paramDto) {

        if (StringUtils.isBlank(paramDto.getAuthorName()) || StringUtils.isBlank(paramDto.getId())) {
            throw new IllegalArgumentException("Ошибка параметров");
        }

        log.info("обновление автора: {}", paramDto.getAuthorName());

        try {
            final Author author = Author.builder().id(paramDto.getId()).name(paramDto.getAuthorName()).build();
            final Author savedAuthor = authorRepository.save(author);
            return AuthorDto.toDto(savedAuthor);
        } catch (Exception e) {
            log.error("Ошибка обновления автора: {} ", paramDto.getAuthorName());
            return null;
        }
    }

    @Override
    @Transactional
    public Boolean deleteAuthor(String id) {
        log.info("удаление автора с id: {}", id);

        try {
            authorRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Ошибка удаления автора с id: {} ", id);
            return false;
        }
    }

}
