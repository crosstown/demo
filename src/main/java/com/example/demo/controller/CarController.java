package com.example.demo.controller;
import com.example.demo.DTO.CarDto;
import com.example.demo.domain.Car;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car savedCar = carRepository.save(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        car.setId(id);
        Car savedCar = carRepository.save(car);
        return new ResponseEntity<>(savedCar, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id) {
        Car car = carRepository.findById(id).orElse(null);
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        List<Car> cars = carRepository.findAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCarFilters() {
        List<Car> cars = carService.getFilters();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<CarDto>> presentar() {
        List<CarDto> cars = carService.presentarAutos();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}