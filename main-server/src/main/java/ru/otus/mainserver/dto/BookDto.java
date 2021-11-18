package ru.otus.mainserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String id;

    private String name;

    private String genre;

    private String author;

}
