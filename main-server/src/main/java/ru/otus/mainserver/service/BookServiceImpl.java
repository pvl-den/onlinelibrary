package ru.otus.mainserver.service;

import org.springframework.stereotype.Service;
import ru.otus.mainserver.dto.BookDto;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    @Override
    public List<BookDto> getAll() {
        return null;
    }
}
