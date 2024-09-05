package org.suhodo.carserver.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.suhodo.carserver.domain.User;

import java.util.Arrays;

@SpringBootTest
@Log4j2
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsertUser(){
        BCryptPasswordEncoder criptor = new BCryptPasswordEncoder();

        User user0 = User.builder()
                .username("user")
                .password(criptor.encode("user"))
                .role("USER")
                .build();
        User user1 = User.builder()
                .username("admin")
                .password(criptor.encode("admin"))
                .role("ADMIN")
                .build();
        User user2 = User.builder()
                .username("suhodo")
                .password(criptor.encode("1234"))
                .role("USER")
                .build();
        User user3 = User.builder()
                .username("bitcamp")
                .password(criptor.encode("1234"))
                .role("ADMIN")
                .build();

        userRepository.saveAll(Arrays.asList(user0, user1, user2, user3));
    }
}
