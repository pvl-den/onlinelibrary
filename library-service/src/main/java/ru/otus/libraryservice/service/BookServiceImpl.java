package ru.otus.libraryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.libraryservice.dto.BookDto;
import ru.otus.libraryservice.entity.Author;
import ru.otus.libraryservice.entity.Book;
import ru.otus.libraryservice.entity.Genre;
import ru.otus.libraryservice.repository.AuthorRepository;
import ru.otus.libraryservice.repository.BookRepository;
import ru.otus.libraryservice.repository.GenreRepository;
import ru.otus.libraryservice.rest.dto.ParamDto;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getById(String id) {
        Book book = bookRepository.findById(id).orElse(null);
        return book != null ? BookDto.toDto(book) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getByName(String name) {
        try {
            final Book book = bookRepository.findByName(name);
            return BookDto.toDto(book);
        } catch (Exception e) {
            log.error("Ошибка поиска книги по имени: {} ", name);
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> books() {
        return bookRepository.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(final String id) {
        try {
            bookRepository.deleteById(id);
            log.info("Книга удалена id {}", id);
        } catch (Exception e) {
            log.error("Ошибка удаления книги с id: {}", id);
        }
    }

    @Override
    @Transactional
    public BookDto createBook(final ParamDto paramBookDto) {
        log.info("Создание книги с параметрами name: {} authorName: {} genreName: {}", paramBookDto.getName(), paramBookDto.getAuthorName(), paramBookDto.getGenreName());

        if (!checkBookParameter(paramBookDto)) {
            log.error("ошибка создания книги name: {} authorName: {} genreName: {}", paramBookDto.getName(), paramBookDto.getAuthorName(), paramBookDto.getGenreName());
            return null;
        }

        final Author author = authorRepository.findByName(paramBookDto.getAuthorName());

        if (author == null) {
            log.error("Ошибка поиска автора");
            return null;
        }

        final Genre genre = genreRepository.findByName(paramBookDto.getGenreName());

        if (genre == null) {
            log.error("Ошибка поиска жанра");
            return null;
        }

        try {
            final Book book = Book.builder()
                    .name(paramBookDto.getName())
                    .author(author)
                    .genre(genre)
                    .build();
            final Book savedBook = bookRepository.save(book);

            log.info("Книга сохранена: {} ", savedBook.getName());
            return BookDto.toDto(savedBook);
        } catch (Exception e) {
            log.error("Ошибка создания книги");
            throw new IllegalArgumentException("Ошибка создания книги");
        }
    }

    private boolean checkBookParameter(final ParamDto paramBookDto) {
        if (paramBookDto == null) return false;
        return StringUtils.isNotBlank(paramBookDto.getName()) && StringUtils.isNotBlank(paramBookDto.getAuthorName()) && StringUtils.isNotBlank(paramBookDto.getGenreName());
    }
}