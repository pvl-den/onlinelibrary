package ru.otus.libraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.libraryservice.entity.Author;
import ru.otus.libraryservice.entity.Book;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private String id;

    private String name;

    public static AuthorDto toDto(Author author) {
        if (author == null) return AuthorDto.builder().build();
        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }

    public static Author toEntity(AuthorDto author) {
        return Author.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }

}
