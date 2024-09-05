package org.suhodo.carserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    // 우리가 인증한 UserDetailsService객체에 암호화 객체 설정을 추가한다.
    // 암호를 DB에 저장하기 전에 BCrypt 암호화 처리
    // 인증과정에서도 입력된 암호를 암호화 하고, DBMS의 암호와 비교
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    // Spring Security의 보안 설정/주소 권한 허용...
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
