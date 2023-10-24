package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class user {
	@Id
	private Integer user_id;
	private String user_name;
	private String user_mail;
	private String user_pass;
	private Boolean user_type;
}
