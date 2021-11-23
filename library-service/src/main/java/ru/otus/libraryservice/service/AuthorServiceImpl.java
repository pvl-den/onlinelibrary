package ru.otus.libraryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.libraryservice.dto.AuthorDto;
import ru.otus.libraryservice.entity.Author;
import ru.otus.libraryservice.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public long count() {
        return authorRepository.count();
    }

    @Override
    @Transactional
    public AuthorDto save(final AuthorDto author) {
        try {
        Author savedAuthor = authorRepository.save(AuthorDto.toEntity(author));
        return AuthorDto.toDto(savedAuthor);
    }catch (Exception e){
            log.error("Ошибка сохранения автора: ");
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDto getById(final String id) {
        Author author = authorRepository.findById(id).orElse(null);

        return AuthorDto.toDto(author);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDto getByName(String authorName) {
        Author author = authorRepository.findByName(authorName);

        return AuthorDto.toDto(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> authors() {
        return authorRepository.findAll().stream().map(AuthorDto::toDto).collect(Collectors.toList());
    }



    @Override
    @Transactional
    public void deleteById(final String id) {
        authorRepository.deleteById(id);
    }
}
