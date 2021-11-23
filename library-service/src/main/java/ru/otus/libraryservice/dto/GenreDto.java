package ru.otus.libraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.libraryservice.entity.Genre;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {

    private String id;

    private String name;

    public static GenreDto toDto(Genre genre) {
        if (genre == null) return GenreDto.builder().build();
        return GenreDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
