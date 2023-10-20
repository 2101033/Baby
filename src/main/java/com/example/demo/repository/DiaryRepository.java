package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.diary;

public interface DiaryRepository extends CrudRepository<diary, Integer> {

}
