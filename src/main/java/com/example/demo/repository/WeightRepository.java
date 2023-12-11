package com.example.demo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.weight;

public interface WeightRepository extends CrudRepository<weight, Integer> {

	@Query("SELECT * FROM weight WHERE baby_id = :id AND DATE_FORMAT(weight_date, '%Y%m') = :yyyymm")
	public Iterable<weight> getAllWeightByBabyId(@Param("id") Integer id, @Param("yyyymm") String yyyymm);
}
