package org.suhodo.carserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhodo.carserver.domain.Car;

import java.util.List;

/* JpaRepository의 상속만 받아도
자동으로 Jpa에 의해 bean으로 생성되어 Spring Container에 포함되게 된다.
* */
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrand(String brand);

    List<Car> findByColor(String color);
}
