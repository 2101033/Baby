package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BabyNewRegisterForm {
	@NotEmpty
	private String baby_name;
	@NotEmpty
	private String birth;
	@NotEmpty
	private String sex;
	private MultipartFile profiel_image;
	private String mail;
}
