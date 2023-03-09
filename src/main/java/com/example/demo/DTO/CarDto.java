package com.example.demo.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CarDto {
    Long id;
    String brand;
    LocalDate date;
    String msg;
}
