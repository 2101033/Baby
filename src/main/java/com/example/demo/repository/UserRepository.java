package com.example.demo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.user;

public interface UserRepository extends CrudRepository<user, Integer> {

	@Query("SELECT * FROM user WHERE user_mail = :email")
	public user getUserByEmail(@Param("email") String email);
	
	@Query("SELECT * FROM user WHERE user_mail = :email AND user_pass = :pass")
	public user getUserByEmailAndPass(@Param("email") String email, @Param("pass") String pass);
}
