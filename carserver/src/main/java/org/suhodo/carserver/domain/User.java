package org.suhodo.carserver.domain;

import lombok.*;

import javax.persistence.*;

/*
@Table(name="account")
User 클래스가 account라는 테이블로 만들어진다.
*/
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;
}
