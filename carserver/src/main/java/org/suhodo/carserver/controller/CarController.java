package org.suhodo.carserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.suhodo.carserver.domain.Car;
import org.suhodo.carserver.repository.CarRepository;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarRepository carRepository;

    @RequestMapping("/cars")
    public Iterable<Car> getCars() {
        return carRepository.findAll();
    }
}
