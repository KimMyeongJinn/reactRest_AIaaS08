package org.suhodo.carserver.domain;

import lombok.Data;
import lombok.Getter;

// 클라이언트가 전송하는 username/password를 저장하기 위한 클래스
// 인증 과정에서 필요한 클래스
@Data
public class AccountCredentials {
    private String username;
    private String password;
}
