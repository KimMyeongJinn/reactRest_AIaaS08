package org.suhodo.carserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "cars")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    private String firstName;
    private String lastName;

    /*
    * @OneToMany 경우  mappedBy = "owner"를 하지 않게 되면
    * (owner는 Car 클래스의 필드명)
    *  교차 테이블이 생성되게 된다.
    * 1대 N 관계로 정하기 위해 아래처럼 설정한다.
    **/
    @JsonIgnore         // json으로 전송할 때 제외한다.
    @OneToMany(mappedBy = "owner",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Car> cars;
}















