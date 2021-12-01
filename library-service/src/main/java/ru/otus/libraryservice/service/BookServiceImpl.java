package ru.otus.libraryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.libraryservice.core.LibraryException;
import ru.otus.libraryservice.core.Response;
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
    public Response<BookDto> getById(String id) throws LibraryException {
        try {
            Book book = bookRepository.findById(id).orElse(null);
            return new Response(BookDto.toDto(book));
        } catch (Exception e) {
            log.error("ошибка поиска книги с id {}", id);
            throw new LibraryException("ошибка поиска книги с id " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Response<BookDto> getByName(String name) throws LibraryException {
        try {
            final Book book = bookRepository.findByName(name);
            return new Response(BookDto.toDto(book));
        } catch (Exception e) {
            log.error("Ошибка поиска книги по имени: {} ", name);
            throw new LibraryException("ошибка поиска с именем " + name);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Response<List<BookDto>> books() throws LibraryException {
        final List<BookDto> books = bookRepository.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
        return new Response<>(books);
    }

    @Override
    @Transactional
    public Response<Boolean> deleteById(final String id) throws LibraryException {
        try {
            bookRepository.deleteById(id);
            log.info("Книга удалена id {}", id);
            return new Response<>(true);
        } catch (Exception e) {
            log.error("Ошибка удаления книги с id: {}", id);
            throw new LibraryException("ошибка удаления книги с id " + id);
        }
    }

    @Override
    @Transactional
    public Response<BookDto> createBook(final ParamDto paramBookDto) throws LibraryException {
        log.info("Создание книги с параметрами name: {} authorName: {} genreName: {}", paramBookDto.getName(), paramBookDto.getAuthorName(), paramBookDto.getGenreName());

        if (!checkBookParameter(paramBookDto)) {
            log.error("ошибка создания книги name: {} authorName: {} genreName: {}", paramBookDto.getName(), paramBookDto.getAuthorName(), paramBookDto.getGenreName());
            throw new LibraryException("ошибка параметров создания книги: " + paramBookDto.getName());
        }

        final Author author = authorRepository.findByName(paramBookDto.getAuthorName());

        if (author == null) {
            log.error("Ошибка поиска автора");
            throw new LibraryException("ошибка поиска автора при создании книги: " + paramBookDto.getName());
        }

        final Genre genre = genreRepository.findByName(paramBookDto.getGenreName());

        if (genre == null) {
            log.error("Ошибка поиска жанра");
            throw new LibraryException("ошибка поиска жанра при создании книги: " + paramBookDto.getName());
        }

        try {
            final Book book = Book.builder()
                    .name(paramBookDto.getName())
                    .author(author)
                    .genre(genre)
                    .build();
            final Book savedBook = bookRepository.save(book);

            log.info("Книга сохранена: {} ", savedBook.getName());
            return new Response<>(BookDto.toDto(savedBook));
        } catch (Exception e) {
            log.error("Ошибка создания книги");
            throw new LibraryException("ошибка создания книги");
        }
    }

    private boolean checkBookParameter(final ParamDto paramBookDto) {
        if (paramBookDto == null) return false;
        return StringUtils.isNotBlank(paramBookDto.getName()) && StringUtils.isNotBlank(paramBookDto.getAuthorName()) && StringUtils.isNotBlank(paramBookDto.getGenreName());
    }
}