package ru.otus.mainserver.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthedUserRepository extends JpaRepository<AuthedUser, Long> {

    AuthedUser findByUsername(String username);

}
