package org.suhodo.carserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhodo.carserver.domain.Car;

import java.util.List;
/* JpaRepository의 상속만 받아도
자동으로 Jpa에 의해 bean으로 생성되어 Spring Container에 포함되게 된다.
* */
/*
'org.springframework.boot:spring-boot-starter-data-rest'
를 프로젝트에 포함시키면
자동으로 모든 Repository는 RestController의 기능도 겸하게 된다.
spring.data.rest.base-path=/api로 application.properties에 등록을 하게 되면
http://서버주소:포트/api로 요청하게 되면
어플리케이션이 제공할 수 있는 api 링크가 제공된다.
* */

/*
1. 전체 조회
    GET - http://localhost:12000/api/cars
2. ID 1인 car 조회
    GET - http://localhost:12000/api/cars/1
3. ID 1인 car의 owner조회
    GET - http://localhost:12000/api/cars/1/owner
4. 새로운 car 생성
    POST - http://localhost:12000/api/cars
     Body > raw > JSON
     전송
     {
        "brand":"Samsung",
        "model":"SM-5",
        "color":"black",
        "registerNumber":"SSS-111",
        "year":2024,
        "price":3200
     }
5. 새로운 owner를 추가
    POST - http://localhost:12000/api/owners
     Body > raw > JSON
     전송
     {
        "firstName":"홍",
        "lastName":"길동"
     }
6. 새로 추가한 ID가 11인 car의 owner 등록
    PUT - http://localhost:12000/api/cars/11/owner

    Header > Content-Type : text/uri-list
    Body > raw > Text
    http://localhost:12000/api/owners/4
    전송
* */

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrand(String brand);

    List<Car> findByColor(String color);
}
