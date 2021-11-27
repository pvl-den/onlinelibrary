package ru.otus.mainserver.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import ru.otus.mainserver.dto.AuthorDto;
import ru.otus.mainserver.dto.GenreDto;
import ru.otus.mainserver.feign.AuthorFeign;
import ru.otus.mainserver.rest.dto.ParamDto;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorFeign authorFeign;

    public AuthorServiceImpl(AuthorFeign authorFeign) {
        this.authorFeign = authorFeign;
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyAuthorCollection")
    public List<AuthorDto> authors() {
        return authorFeign.authors();
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyAuthor")
    public AuthorDto getById(String id) {
        return authorFeign.getById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyAuthor")
    public AuthorDto getByName(String authorName) {
        return authorFeign.getByName(authorName);
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyAuthor")
    public AuthorDto createAuthor(ParamDto paramDto) {
        return authorFeign.createAuthor(paramDto);
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyAuthor")
    public AuthorDto updateAuthor(ParamDto paramDto) {
        return authorFeign.updateAuthor(paramDto);
    }

    @Override
    @HystrixCommand(fallbackMethod = "falseAuthor")
    public Boolean deleteAuthor(String id) {
        return authorFeign.deleteAuthor(id);
    }

    AuthorDto emptyAuthor(ParamDto paramDto) {
        return AuthorDto.builder().build();
    }

    AuthorDto emptyAuthor(String id) {
        return AuthorDto.builder().build();
    }

    Boolean falseAuthor(String id) {
        return false;
    }

    List<AuthorDto> emptyAuthorCollection() {
        return List.of(AuthorDto.builder().build());
    }
}
