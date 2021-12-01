package ru.otus.libraryservice.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private String errorMessage;

    private T data;

    public Response(final T data) {
        super();
        this.data = data;
        this.errorMessage = null;
    }


    public Response(final String errorMessage) {
        super();
        this.data = null;
        this.errorMessage = errorMessage;
    }

    public final Boolean isValid() {
        return data != null && errorMessage == null;
    }

    public final Boolean isNotValid() {
        return data == null || errorMessage != null;
    }

}
