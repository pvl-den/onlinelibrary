package ru.otus.mainserver.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class LibraryException extends Exception{

    public LibraryException(String message) {
        super(message);
    }
}
