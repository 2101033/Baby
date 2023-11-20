package com.example.demo.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvNewRegisterForm {
	@NotEmpty
	private String code;
	private String mail;
}
