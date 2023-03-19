package com.example.demo.controller;

import com.example.demo.DTO.CarDto;
import com.example.demo.domain.Car;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCarController {
    @Mock
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarController carController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCar() {
        Car car = new Car();
        when(carRepository.save(car)).thenReturn(car);
        ResponseEntity<Car> response = carController.createCar(car);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(car, response.getBody());
    }

    @Test
    public void testUpdateCar() {
        Long id = 1L;
        Car car = new Car();
        car.setId(id);
        when(carRepository.save(car)).thenReturn(car);
        ResponseEntity<Car> response = carController.updateCar(id, car);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(car, response.getBody());
    }

    @Test
    public void testGetCar() {
        Long id = 1L;
        Car car = new Car();
        car.setId(id);
        when(carRepository.findById(id)).thenReturn(Optional.of(car));
        ResponseEntity<Car> response = carController.getCar(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(car, response.getBody());
    }

    @Test
    public void testGetCars() {
        List<Car> cars = new ArrayList<>();
        when(carRepository.findAll()).thenReturn(cars);
        ResponseEntity<List<Car>> response = carController.getCars();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    public void testGetCarFilters() {
        List<Car> cars = new ArrayList<>();
        when(carService.getFilters()).thenReturn(cars);
        ResponseEntity<List<Car>> response = carController.getCarFilters();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    public void testdoing() {
        String cars = new String();
        when(carService.doing()).thenReturn(cars);
        ResponseEntity<String> response = carController.getDoing();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    public void testPresentar() {
        List<CarDto> carDtos = new ArrayList<>();
        when(carService.presentarAutos()).thenReturn(carDtos);
        ResponseEntity<List<CarDto>> response = carController.presentar();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carDtos, response.getBody());
    }
}