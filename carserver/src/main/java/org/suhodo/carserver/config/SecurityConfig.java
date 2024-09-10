package org.suhodo.carserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.suhodo.carserver.except.AuthEntryPoint;
import org.suhodo.carserver.filter.AuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final AuthenticationFilter authenticationFilter;
    private final AuthEntryPoint authEntryPoint;

    /* Spring Security내부에서 인증과정시 사용하는 AuthenticationManager객체를
    LoginController에서 접근할 수 있도록
    Spring Container의 Bean으로 등록시켜준다.
    * */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

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
        http.csrf().disable()   // CSRF는 세션을 사용하는데 Rest API 서버로 작동하기 때문에 disable해도 된다.
                .cors().and()   // CORS는 사용한다.
                // Rest API 서버는 세션 상태를 유지하지 않으므로 STATELESS
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // /login엔드포인트에 대한 POST요청은 누구나 접근을 허용함
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                // 임시로 모든 요청을 허용한다
//                .anyRequest().permitAll();
                // 다른 엔트포인트 요청은 인증 과정을 거쳐야 접근할 수 있다.
                .anyRequest().authenticated().and()
                // /login을 제외한 나머지 모든 요청은 필터를 인증 전단계에서 거치게 된다.
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // 인증에 오류가 있을 때, 오류 응답 처리를 authEntryPoint가 담당한다.
                .exceptionHandling().authenticationEntryPoint(authEntryPoint);
    }

    // CORS(Cross-Origin Resource Sharing)
    // REST API Server 는 허용해줘야 한다.
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "https://www.bitcamp.co.kr"));
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(false);
        config.applyPermitDefaultValues();

        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
