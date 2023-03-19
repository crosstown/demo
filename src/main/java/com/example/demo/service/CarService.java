package com.example.demo.service;

import com.example.demo.DTO.CarDto;
import com.example.demo.domain.Car;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CarService {
    public List<Car> getFilters() {
        List<Car> cars = new ArrayList<>();
        cars.add(createCarBlue());
        cars.add(createCarGreen());
        cars.add(createCarRojo());
       return  cars.stream()
                .filter((car) -> car.getPrice() > 2000)
                .collect(Collectors.toList());

    }

    public List<CarDto> presentarAutos() {
        List<Car> cars = new ArrayList<>();
        cars.add(createCarBlue());
        cars.add(createCarGreen());
        cars.add(createCarRojo());

        return cars.stream()
                .map (car -> {
                    CarDto dto = new CarDto();
                    dto.setId(car.getId());
                    dto.setBrand(car.getBrand());
                    dto.setDate(LocalDate.of(2016, 9, 23));
                    dto.setMsg(car.getBrand() + " " + car.getColor());
                    return dto;
                }).collect(Collectors.toList());

    }
    public String doing () {
        List<Car> cars = new ArrayList<>();
        cars.add(createCarBlue());
        cars.add(createCarGreen());
        cars.add(createCarRojo());

        return cars.stream()
                .map(Car::getBrand)
                .distinct()
                .collect(Collectors.joining(" ;"));
    }
    private List<Car> sorted() {
        List<Car> autos = new ArrayList<>();
        autos.add(createCarGreen());
        autos.add(createCarBlue());
        autos.add(createCarRojo());

        autos.sort(Comparator.comparing(Car::getBrand).thenComparing(Car::getColor));
        return autos;
    }



    private Optional<Integer> getDays(LinkedHashMap<String, Object> map) {
        return map.values().stream()
                .filter(value -> value instanceof LinkedHashMap)
                .map(value -> getDays((LinkedHashMap<String, Object>) value))
                .filter(Optional::isPresent)
                .findFirst()
                .orElseGet(() -> {
                    Object allowDaysValue = map.get("automaticEnrollmentAllowWithdrawalDays");
                    if (allowDaysValue instanceof Number) {
                        return Optional.of(((Number) allowDaysValue).intValue());
                    } else {
                        return Optional.empty();
                    }
                });
    }

    private Car createCarRojo () {
        Car rojo = Car.builder()
                .color("Red")
                .id(100L)
                .brand("Ford")
                .price(2000.0)
                .build();
        return rojo;
    }
    private Car createCarBlue () {
        Car blue = Car.builder()
                .color("Blue")
                .id(101L)
                .brand("Audi")
                .price(45000.0)
                .build();
        return blue;
    }
    private Car createCarGreen () {
        Car verde = Car.builder()
                .color("Green")
                .id(102L)
                .brand("Ford")
                .price(2001.0)
                .build();
        return verde;
    }

}
