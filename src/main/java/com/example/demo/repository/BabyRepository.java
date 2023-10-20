package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.baby;

public interface BabyRepository extends CrudRepository<baby, Integer> {

}
