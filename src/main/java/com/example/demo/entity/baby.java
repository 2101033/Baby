package com.example.demo.entity;

import org.springframework.data.annotation.Id;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class baby {
	@Id
	private Integer baby_id;
	private String baby_name;
	private String birth;
	private String sex;
	private String profiel_image;
	private Integer user_id;	
}
