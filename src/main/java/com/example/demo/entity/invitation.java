package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class invitation {
	@Id
	private Integer  invitation_id;
	private String invitation_code;
	private Integer host_user_id;
	private Integer view_user_id;
}
