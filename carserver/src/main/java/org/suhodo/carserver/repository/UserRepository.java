package org.suhodo.carserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.suhodo.carserver.domain.User;

import java.util.Optional;

// UserRepository는 RestController 역할을 하지 않는다.
@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
