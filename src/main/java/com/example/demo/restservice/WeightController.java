package com.example.demo.restservice;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BabyService;

@RestController
@RequestMapping("weight")
public class WeightController {

	@Autowired
	BabyService babyService;
	
	@GetMapping("/get")
	public Map<LocalDateTime, Double> getWeightByBabyId(@RequestParam(value = "babyId") Integer babyId, @RequestParam(value = "yyyymm") String yyyymm) {
		return babyService.getWeightByBabyId(babyId, yyyymm);
		
	}
}
