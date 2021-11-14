package ru.otus.libraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.libraryservice.entity.Book;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String id;

    private String name;

    private String genre;

    private String author;

    public static BookDto toDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .genre(book.getGenre().getName())
                .author(book.getAuthor().getName())
                .build();
    }
}
