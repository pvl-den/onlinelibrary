package ru.otus.mainserver.service.user;

import ru.otus.mainserver.dto.user.AuthedUserDto;
import ru.otus.mainserver.security.AuthedUser;

import java.util.List;

public interface UserService {

    public List<AuthedUserDto> users();

    public AuthedUserDto getById(Long id);

    public AuthedUserDto getByName(String userName);

    public AuthedUserDto createUser(AuthedUserDto paramDto);

    public AuthedUserDto updateUser(AuthedUserDto paramDto);

    public Boolean deleteUser(Long id);
}
