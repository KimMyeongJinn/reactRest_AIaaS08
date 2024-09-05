package org.suhodo.carserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.suhodo.carserver.domain.User;
import org.suhodo.carserver.repository.UserRepository;
import org.springframework.security.core.userdetails.User.UserBuilder;

import java.util.Optional;

/* Spring Security(AuthenticationManager객체)의 인증 과정에서 호출되어 진다.
사용자의 이름을 loadUserByUsername(String username)의 매개변수로 전달받는다.
이곳에서는 DBMS의 User테이블에 username을 가진 사용자가 있는 지 조회하여
존재하지 않으면 예외처리
존재하면 UserDetails객체를 생성해서 리턴해주면 된다.
* */
@Service
@Log4j2
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername = " + username);
        Optional<User> user = userRepository.findByUsername(username);

        UserBuilder builder = null;
        // username에 해당하는 User객체가 존재한다면
        if(user.isPresent()) {
            User currentUser = user.get();
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(currentUser.getPassword());
            builder.roles(currentUser.getRole());
        }else{
            throw new UsernameNotFoundException("user Not Found : " + username);
        }
        return builder.build();
    }
}
