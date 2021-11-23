package ru.otus.mainserver.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.feign.BookFeign;
import ru.otus.mainserver.rest.dto.ParamBookDto;

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
    public BookDto createBook(ParamBookDto paramBookDto) {
        return bookFeign.createBook(paramBookDto);

    }

    BookDto emptyBook(ParamBookDto paramBookDto){
        return BookDto.builder().build();
    }

    List<BookDto> emptyBookCollection() {
        return List.of(BookDto.builder().build());
    }
}
