package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BabyNewRegisterForm {
	private String baby_name;
	private String birth;
	private String sex;
	private MultipartFile profiel_image;
	private String mail;
}
