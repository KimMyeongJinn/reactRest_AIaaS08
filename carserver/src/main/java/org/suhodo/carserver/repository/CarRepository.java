package org.suhodo.carserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhodo.carserver.domain.Car;

import java.util.List;
/*
'org.springframework.boot:spring-boot-starter-data-rest'
를 프로젝트에 포함시키면
자동으로 모든 Repository는 RestController의 기능도 겸하게 된다.
spring.data.rest.base-path=/api로 application.properties에 등록을 하게 되면
http://서버주소:포트/api로 요청하게 되면
어플리케이션이 제공할 수 있는 api 링크가 제공된다.
* */

/* JpaRepository의 상속만 받아도
자동으로 Jpa에 의해 bean으로 생성되어 Spring Container에 포함되게 된다.
* */
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrand(String brand);

    List<Car> findByColor(String color);
}
