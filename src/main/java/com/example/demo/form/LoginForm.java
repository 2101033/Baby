package com.example.demo.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {

	@NotEmpty
	private String email;
	
	private String password;
	
}
