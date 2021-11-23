package ru.otus.libraryservice.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.libraryservice.entity.Book;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamBookDto {


    private String name;

    private String genreName;

    private String authorName;

}
