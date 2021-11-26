package ru.otus.mainserver.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamDto {

    private String id;

    private String name;

    private String genreName;

    private String authorName;

}
