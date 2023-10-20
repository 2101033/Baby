package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class user {
	private Integer user_id;
	private String user_name;
	private String user_mail;
	private String user_pass;
	private boolean user_type;
}
