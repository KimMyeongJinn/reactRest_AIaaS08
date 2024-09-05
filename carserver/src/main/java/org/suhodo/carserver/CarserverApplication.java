package org.suhodo.carserver;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.suhodo.carserver.domain.Car;
import org.suhodo.carserver.domain.Owner;
import org.suhodo.carserver.repository.CarRepository;
import org.suhodo.carserver.repository.OwnerRepository;

import java.util.Arrays;

@Log4j2
@SpringBootApplication
@RequiredArgsConstructor
public class CarserverApplication implements CommandLineRunner {

    private final OwnerRepository ownerRepository;
    private final CarRepository carRepository;

    public static void main(String[] args) {
        SpringApplication.run(CarserverApplication.class, args);
        log.info("CarServer Application Started................");
    }


    // 어플리케이션이 실행될 때 자동으로 호출됨
    @Override
    public void run(String... args) throws Exception {
        Owner owner0 = Owner.builder()
                .firstName("John")
                .lastName("Smith")
                .build();
        Owner owner1 = Owner.builder()
                .firstName("Henry")
                .lastName("Fonda")
                .build();
        Owner owner2 = Owner.builder()
                .firstName("Tomas")
                .lastName("Cruise")
                .build();
        ownerRepository.saveAll(Arrays.asList(owner0, owner1, owner2));

        Car car0 = Car.builder()
                .brand("Ford")
                .model("Mustang")
                .color("white")
                .registerNumber("AAA-111")
                .year(2024)
                .price(6400)
                .owner(owner0)
                .build();
        Car car1 = Car.builder()
                .brand("Hyndai")
                .model("Genesis")
                .color("black")
                .registerNumber("HHH-111")
                .year(2024)
                .price(8500)
                .owner(owner1)
                .build();
        Car car2 = Car.builder()
                .brand("Kia")
                .model("Sorento")
                .color("gray")
                .registerNumber("KKK-111")
                .year(2024)
                .price(4300)
                .owner(owner2)
                .build();
        Car car3 = Car.builder()
                .brand("현대")
                .model("그랜저")
                .color("black")
                .registerNumber("HHH-2222")
                .year(2024)
                .price(8500)
                .owner(owner0)
                .build();
        Car car4 = Car.builder()
                .brand("기아")
                .model("뉴카렌스")
                .color("silver")
                .registerNumber("KKK-222")
                .year(2024)
                .price(4300)
                .owner(owner1)
                .build();
        carRepository.saveAll(Arrays.asList(car0, car1, car2, car3, car4));
//        carRepository.save(car0);
//        carRepository.save(car1);
//        carRepository.save(car2);

    }
}
