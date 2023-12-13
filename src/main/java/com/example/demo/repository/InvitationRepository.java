package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.invitation;
import com.example.demo.entity.user;

public interface InvitationRepository extends CrudRepository<invitation, Integer> {
	@Query("SELECT * FROM baby WHERE invitation_code = :code")
	public invitation invitationCheck(@Param("code") String code);
	
	@Query("SELECT * FROM invitation WHERE host_user_id = :user_id")
	public Optional<invitation> invitationHost_User_Id(@Param("user_id") Integer user_id);
}
