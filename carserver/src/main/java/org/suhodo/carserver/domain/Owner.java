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
    /* CarController에 요청할 때
      Car에서는 Owner의 데이터도 조회해서 json으로 전송하는데
      이때 다시 자식인 cars를 조회하는 순환 조회가 일어나므로
      무한 루프에 빠져 StackOverflow가 일어나면 예외가 발생한다.
      그러므로 json데이터를 보내지 않는 설정을 해준다.
    * */
    @JsonIgnore         // json으로 전송할 때 제외한다.
    @OneToMany(mappedBy = "owner",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Car> cars;
}















