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
        log.info("초기화 작업...........");

    }
}
