package ru.otus.mainserver.rest.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.mainserver.dto.AuthorDto;
import ru.otus.mainserver.dto.user.AuthedUserDto;
import ru.otus.mainserver.rest.dto.ParamDto;
import ru.otus.mainserver.security.AuthedUser;
import ru.otus.mainserver.service.AuthorService;
import ru.otus.mainserver.service.user.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @CrossOrigin
    @GetMapping("/user")
    public List<AuthedUserDto> users() {
        return userService.users();
    }

    @CrossOrigin
    @GetMapping("/user/byId{id}")
    public AuthedUserDto userById(@PathVariable("id") final Long id) {
        return userService.getById(id);
    }

    @CrossOrigin
    @GetMapping("/user/byName/{userName}")
    public AuthedUserDto userByName(@PathVariable("userName") final String userName) {
        return userService.getByName(userName);
    }

    @CrossOrigin
    @PostMapping("/user/create")
    public AuthedUserDto createUser(@RequestBody AuthedUserDto paramDto){
        return userService.createUser(paramDto);
    }

    @CrossOrigin
    @PutMapping("/user/update")
    public AuthedUserDto updateUser(@RequestBody AuthedUserDto paramDto){
        return userService.updateUser(paramDto);
    }

    @CrossOrigin
    @DeleteMapping("/user/delete/{id}")
    public Boolean deleteUser(@PathVariable("id") final Long id){
        return userService.deleteUser(id);
    }
}
