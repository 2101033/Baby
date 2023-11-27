package com.example.demo.restservice;

import java.time.LocalDateTime;

public record weight(Integer id, LocalDateTime date, Double weight, Integer babyId) {

}
