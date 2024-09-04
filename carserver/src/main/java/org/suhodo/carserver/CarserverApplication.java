package org.suhodo.carserver;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.suhodo.carserver.domain.Car;
import org.suhodo.carserver.repository.CarRepository;

import java.util.Arrays;

@Log4j2
@SpringBootApplication
@RequiredArgsConstructor
public class CarserverApplication implements CommandLineRunner {

    private final CarRepository carRepository;

    public static void main(String[] args) {
        SpringApplication.run(CarserverApplication.class, args);
        log.info("CarServer Application Started................");
    }


    // 어플리케이션이 실행될 때 자동으로 호출됨
    @Override
    public void run(String... args) throws Exception {

        Car car0 = Car.builder()
                .brand("Ford")
                .model("Mustang")
                .color("white")
                .registerNumber("AAA-111")
                .year(2024)
                .price(6400)
                .build();
        Car car1 = Car.builder()
                .brand("Hyndai")
                .model("Genesis")
                .color("black")
                .registerNumber("HHH-111")
                .year(2024)
                .price(8500)
                .build();
        Car car2 = Car.builder()
                .brand("Kia")
                .model("Sorento")
                .color("gray")
                .registerNumber("KKK-111")
                .year(2024)
                .price(4300)
                .build();
        carRepository.saveAll(Arrays.asList(car0, car1, car2));
//        carRepository.save(car0);
//        carRepository.save(car1);
//        carRepository.save(car2);

    }
}
