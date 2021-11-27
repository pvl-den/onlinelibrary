package ru.otus.mainserver.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.mainserver.security.AuthedUser;

public interface UserRepository extends JpaRepository<AuthedUser, Long> {

    AuthedUser findByUsername(String username);

}
