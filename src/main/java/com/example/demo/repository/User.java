package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.user;

public interface User extends CrudRepository<user, Integer> {

}
