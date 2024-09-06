package org.suhodo.carserver.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.suhodo.carserver.service.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* /login 엔드포인트를 제외한 나머지 엔드포인트 요청은
   이곳에서 모두 검증한다.
   제대로된 jwt token인지 아닌지
   Process
   1) token이 있는지 여부 -> 2) token이 정상인지 -> 요청 DispatcherServlet으로 전달
* 
* */
@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1) token이 있는지 여부
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(jwtToken != null) {
            // 2) token이 정상인지
            // 토큰을 비밀키로 복호화해서 username을 추출
            String username = jwtService.getAuthUser(request);
            Authentication auth = 
                    new UsernamePasswordAuthenticationToken(username, 
                            null, java.util.Collections.emptyList());
            
            // 시큐리티 컨텍스트에 저장
            // 서버는 인증정보를 가지게 됨
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        // 3) 요청을 다음 필터 or DispatcherServlet으로 전달
        filterChain.doFilter(request, response);
    }
}










