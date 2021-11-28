package ru.otus.mainserver.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.feign.BookFeign;
import ru.otus.mainserver.rest.dto.ParamDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookFeign bookFeign;

    @Override
    @HystrixCommand(fallbackMethod = "emptyBookCollection")
    public List<BookDto> books() {
        return bookFeign.books();
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyBook")
    public BookDto createBook(ParamDto paramBookDto) {
        return bookFeign.createBook(paramBookDto);

    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyBook")
    public BookDto getById(String id) {
        return bookFeign.getById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyBook")
    public BookDto getByName(String bookName) {
        return bookFeign.getByName(bookName);
    }

    BookDto emptyBook(ParamDto paramBookDto){
        return BookDto.builder().build();
    }
    BookDto emptyBook(String bookName){
        return BookDto.builder().build();
    }

    List<BookDto> emptyBookCollection() {
        return List.of(BookDto.builder().build());
    }
}
