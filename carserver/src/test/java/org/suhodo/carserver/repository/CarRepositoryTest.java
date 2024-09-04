package org.suhodo.carserver.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.suhodo.carserver.domain.Car;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Log4j2
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void testInsertCar(){
        Car car0 = Car.builder()
                .brand("포드")
                .model("무스탕")
                .color("white")
                .registerNumber("AAA-111")
                .year(2024)
                .price(6400)
                .build();
        Car car1 = Car.builder()
                .brand("현대")
                .model("제네시스")
                .color("black")
                .registerNumber("HHH-111")
                .year(2024)
                .price(8500)
                .build();
        Car car2 = Car.builder()
                .brand("기아")
                .model("소렌토")
                .color("gray")
                .registerNumber("KKK-111")
                .year(2024)
                .price(4300)
                .build();
        carRepository.saveAll(Arrays.asList(car0, car1, car2));
    }

    @Test
    public void testFindAllCar(){
        List<Car> cars = carRepository.findAll();
        for(Car car : cars){
            log.info(car);
        }
    }

    @Test
    public void testFindCarByBrandAndColor(){
        for(Car car : carRepository.findByBrand("Ford")){
            log.info(car);
        }

        for(Car car : carRepository.findByColor("gray")){
            log.info(car);
        }
    }
}
