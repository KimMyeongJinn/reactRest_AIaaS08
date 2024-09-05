package org.suhodo.carserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhodo.carserver.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
