package com.example.demo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.invitation;

public interface InvitationRepository extends CrudRepository<invitation, Integer> {
	@Query("SELECT * FROM baby WHERE invitation_code = :code")
	public invitation invitationCheck(@Param("code") String code);
}
