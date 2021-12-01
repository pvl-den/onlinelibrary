package ru.otus.mainserver.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response<T> {

    private T data;
    private String errorMessage;

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
