package ru.otus.mainserver.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.mainserver.dto.user.AuthedUserDto;
import ru.otus.mainserver.repository.user.UserRepository;
import ru.otus.mainserver.security.AuthedUser;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<AuthedUserDto> users() {
        return userRepository.findAll().stream().map(AuthedUserDto::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AuthedUserDto getById(final Long id) {
        final AuthedUser user = userRepository.findById(id).orElse(null);
        return AuthedUserDto.toDto(user);
    }

    @Override
    public AuthedUserDto getByName(final String userName) {
        final AuthedUser user = userRepository.findByUsername(userName);
        return AuthedUserDto.toDto(user);
    }

    @Override
    public AuthedUserDto createUser(AuthedUserDto paramDto) {

        if (StringUtils.isBlank(paramDto.getUsername())) {
            throw new IllegalArgumentException("Ошибка параметров создания юзера");
        }

        log.info("создание юзера: {}", paramDto.getUsername());

        try {
            final AuthedUser oldUser = userRepository.findByUsername(paramDto.getUsername());

            if (oldUser != null) {
                log.error("Пользователь с именем {} уже существует.", oldUser.getUsername());
                return AuthedUserDto.toDto(oldUser);
            }

            final AuthedUser user = AuthedUser.builder()
                    .username(paramDto.getUsername())
                    .password(paramDto.getPassword())
                    .authority("ROLE_USER")
                    .build();

            final AuthedUser savedUser = userRepository.save(user);
            return AuthedUserDto.toDto(savedUser);
        } catch (Exception e) {
            log.error("Ошибка создания юзера: {}", paramDto.getUsername());
            return null;
        }
    }

    @Override
    @Transactional
    public AuthedUserDto updateUser(AuthedUserDto paramDto) {

        final AuthedUser oldUser = userRepository.findByUsername(paramDto.getUsername());

        if (oldUser == null) {
            throw new IllegalArgumentException("Ошибка обновления юзера. Не найден.");
        }

        log.info("обновление юзера: {}", paramDto.getUsername());

        try {

            final AuthedUser newUser = AuthedUser.builder()
                    .id(paramDto.getId())
                    .username(paramDto.getUsername())
                    .password(paramDto.getPassword())
                    .authority(oldUser.getAuthority())
                    .build();

            final AuthedUser savedUser = userRepository.save(newUser);
            return AuthedUserDto.toDto(savedUser);
        } catch (Exception e) {
            log.error("Ошибка обновление юзера: {}", paramDto.getUsername());
            return null;
        }
    }

    @Override
    @Transactional
    public Boolean deleteUser(Long id) {
        log.info("удаление юзера с id: {}", id);

        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Ошибка удаления юзера с id: {} ", id);
            return false;
        }
    }
}