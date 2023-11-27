package com.example.demo.form;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNewRegisterForm {
	@NotEmpty
	private String mail;
	@NotEmpty
	private String pass;
	@NotEmpty
	private String conf_user_pass;
	@NotEmpty
	private String user_name;
	
	private Boolean user_type;
	
	@AssertTrue
	public boolean isPaswordValid() { 
		if(pass == null || pass.isEmpty()) {
			return true;
		}
	return pass.equals(conf_user_pass);
	}
}

