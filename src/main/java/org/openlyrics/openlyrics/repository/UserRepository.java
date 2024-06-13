package org.openlyrics.openlyrics.repository;

import java.util.Optional;

import org.openlyrics.openlyrics.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
}
