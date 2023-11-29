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
	
	/**
	 * 追加しました。 by 山下
	 */
	// private String mail;
    private String password;
    private String user_name;
    private Boolean recView;
}
