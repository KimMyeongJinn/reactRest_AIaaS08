package org.suhodo.carserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.suhodo.carserver.domain.AccountCredentials;
import org.suhodo.carserver.service.JwtService;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtService jwtService;    // 토큰 발급과 토큰에서 username을 추출

    // Spring Security내부에서 생성되는 클래스 객체
    // 로그인/인증 과정을 담당한다.
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials){

        // AuthenticationManager에서 인증과정에 사용하는 객체에
        // 클라이언트가 전송한 username/password를 저장함
        UsernamePasswordAuthenticationToken creds = 
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(), 
                        credentials.getPassword());
        
        /* 아래 메서드가 호출되는 과정에서
        UserDetailsServiceImpl에 오버로딩한 loadUserByUsername이 호출되어
        username을 가진 사용자가 DB에 존재하는지 확인하여 내부적으로 로그인을 처리함
        * */
        Authentication auth = authenticationManager.authenticate(creds);

        // 토큰 발급
        String jwts = jwtService.getToken(auth.getName());

        // 클라이언트에 전송할 데이터의 AUTHORIZATION 헤더에 토큰을 넣어서 전달함
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                .build();
    }
}









