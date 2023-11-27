package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BabyController {

	@GetMapping("profile")
	public String profile() {
		return "Profile";
	}
	
	@GetMapping("invitation")
	public String invitation() {
		return"Invitation";
	}

	@GetMapping("logout")
	public String logout() {
		return"logout";
	}
}
