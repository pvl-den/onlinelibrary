package ru.otus.mainserver.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.mainserver.dto.BookDto;
import ru.otus.mainserver.feign.BookFeign;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookFeign bookFeign;

    @Override
    @HystrixCommand(fallbackMethod = "emptyBookCollection")
    public List<BookDto> getAll() {
        return bookFeign.getAll();
    }

    List<BookDto> emptyBookCollection() {
        return List.of(BookDto.builder().build());
    }
}
