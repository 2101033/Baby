package com.example.demo.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.user;
import com.example.demo.service.BabyService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	BabyService service;

	@GetMapping("/get")
	public User getUser(@RequestParam(value = "mail") String mail) {
		user user = service.getUser(mail);
		
		if (user == null) {
			return new User(null, null, null);
		}
		return new User(user.getUser_id(), mail, user.getUser_name());
	}
}
