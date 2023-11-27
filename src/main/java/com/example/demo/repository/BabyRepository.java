package com.example.demo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.baby;
import com.example.demo.entity.user;

public interface BabyRepository extends CrudRepository<baby, Integer> {
	@Query("SELECT * FROM baby WHERE user_id = :user_id")
	public baby getBabyByfindAll(@Param("user_id") Integer user_id);
}
