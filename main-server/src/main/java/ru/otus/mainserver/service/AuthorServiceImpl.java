package ru.otus.mainserver.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import ru.otus.mainserver.dto.AuthorDto;
import ru.otus.mainserver.feign.AuthorFeign;

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

    List<AuthorDto> emptyAuthorCollection() {
        return List.of(AuthorDto.builder().build());
    }
}
