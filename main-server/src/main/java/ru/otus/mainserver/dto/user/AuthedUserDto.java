package ru.otus.mainserver.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.mainserver.security.AuthedUser;

import javax.persistence.Column;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthedUserDto {

    private Long id;

    private String username;

    private String password;

    private String authority;


    public static AuthedUserDto toDto(AuthedUser user) {
        if (user == null) return AuthedUserDto.builder().build();
        return AuthedUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authority(user.getAuthority())
                .build();
    }

}
