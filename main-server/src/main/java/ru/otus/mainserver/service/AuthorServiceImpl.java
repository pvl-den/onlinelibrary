package ru.otus.mainserver.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import ru.otus.mainserver.core.LibraryException;
import ru.otus.mainserver.core.Response;
import ru.otus.mainserver.dto.AuthorDto;
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
    public Response<List<AuthorDto>> authors() {
        return authorFeign.authors();
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyAuthor")
    public Response<AuthorDto> getById(String id) throws InterruptedException {
        Thread.sleep(10000);
        return authorFeign.getById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyAuthor")
    public Response<AuthorDto> getByName(String authorName) {
        return authorFeign.getByName(authorName);
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyAuthor")
    public Response<AuthorDto> createAuthor(ParamDto paramDto) {
        return authorFeign.createAuthor(paramDto);
    }

    @Override
    @HystrixCommand(fallbackMethod = "emptyAuthor")
    public Response<AuthorDto> updateAuthor(ParamDto paramDto) {
        return authorFeign.updateAuthor(paramDto);
    }

    @Override
    @HystrixCommand(fallbackMethod = "falseAuthor")
    public Response<Boolean> deleteAuthor(String id) {
        return authorFeign.deleteAuthor(id);
    }

    Response<AuthorDto> emptyAuthor(ParamDto paramDto) throws LibraryException {
        return new Response("Привет от fallbackMethod");
    }

    Response<AuthorDto> emptyAuthor(String id) throws LibraryException {
        return new Response("Привет от fallbackMethod");
    }

    Response<Boolean> falseAuthor(String id) throws LibraryException {
        return new Response("Привет от fallbackMethod");
    }

    Response<List<AuthorDto>> emptyAuthorCollection() throws LibraryException {
        return new Response("Привет от fallbackMethod");
    }
}
