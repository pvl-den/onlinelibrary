package ru.otus.libraryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.libraryservice.core.LibraryException;
import ru.otus.libraryservice.core.Response;
import ru.otus.libraryservice.dto.AuthorDto;
import ru.otus.libraryservice.entity.Author;
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
    public Response<AuthorDto> getById(final String id) throws LibraryException {
        final Author author = authorRepository.findById(id).orElse(null);

        if (author == null)
            throw new LibraryException("Не найден автор с id: " + id);

        return new Response<>(AuthorDto.toDto(author));
    }

    @Override
    @Transactional(readOnly = true)
    public Response<AuthorDto> getByName(String authorName) throws LibraryException {
        final Author author = authorRepository.findByName(authorName);

        if (author == null)
            throw new LibraryException("Не найден автор с именем: " + authorName);

        return new Response(AuthorDto.toDto(author));
    }

    @Override
    @Transactional(readOnly = true)
    public Response<List<AuthorDto>> authors() throws LibraryException {
        List<AuthorDto> authors = authorRepository.findAll().stream().map(AuthorDto::toDto).collect(Collectors.toList());

        if (authors.isEmpty())
            throw new LibraryException("Ошибка получения списка авторов ");

        return new Response<>(authors);
    }

    @Override
    @Transactional
    public Response<AuthorDto> createAuthor(ParamDto paramDto) throws LibraryException {

        if (StringUtils.isBlank(paramDto.getAuthorName())) {
            throw new LibraryException("Ошибка параметров при созздании автора");
        }

        log.info("создание автора: {}", paramDto.getAuthorName());

        try {
            final Author author = Author.builder().name(paramDto.getAuthorName()).build();
            final Author savedAuthor = authorRepository.save(author);
            return new Response<>(AuthorDto.toDto(savedAuthor));
        } catch (Exception e) {
            log.error("Ошибка создания автора: {}", paramDto.getAuthorName());
            throw new LibraryException("Ошибка создания автора: " + paramDto.getAuthorName());
        }
    }

    @Override
    @Transactional
    public Response<AuthorDto> updateAuthor(ParamDto paramDto) throws LibraryException {

        if (StringUtils.isBlank(paramDto.getAuthorName()) || StringUtils.isBlank(paramDto.getId())) {
            throw new LibraryException("Ошибка параметров при обновлении автора");
        }

        log.info("обновление автора: {}", paramDto.getAuthorName());

        try {
            final Author author = Author.builder().id(paramDto.getId()).name(paramDto.getAuthorName()).build();
            final Author savedAuthor = authorRepository.save(author);
            return new Response<>(AuthorDto.toDto(savedAuthor));
        } catch (Exception e) {
            log.error("Ошибка обновления автора: {} ", paramDto.getAuthorName());
            throw new LibraryException("Ошибка обновления автора: " + paramDto.getAuthorName());
        }
    }

    @Override
    @Transactional
    public Response<Boolean> deleteAuthor(String id) throws LibraryException {
        log.info("удаление автора с id: {}", id);

        try {
            authorRepository.deleteById(id);
            return new Response(true);
        } catch (Exception e) {
            log.error("Ошибка удаления автора с id: {} ", id);
            throw new LibraryException("Ошибка удаления автора с id: " + id);
        }
    }

}
