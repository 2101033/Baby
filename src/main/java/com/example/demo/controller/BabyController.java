package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BabyController {
	
	@GetMapping("login")
	public String loginView() {
		return "login";
	}
	
	@GetMapping("weight")
	public String weightView() {
		return "weight";
	}
}
