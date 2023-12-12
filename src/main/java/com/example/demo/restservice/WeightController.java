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
	
	@GetMapping("/get/month")
	public Map<LocalDateTime, Double> getMonthlyWeightByBabyId(@RequestParam(value = "babyId") Integer babyId, @RequestParam(value = "yyyymm") String yyyymm) {
		return babyService.getMonthlyWeightByBabyId(babyId, yyyymm);
		
	}
	
	@GetMapping("/get/week")
	public Map<LocalDateTime, Double> getWeeklyWeightByBabyId(@RequestParam(value = "babyId") Integer babyId, @RequestParam(value = "yyyymmdd") String yyyymmdd) {
		return babyService.getWeeklyWeightByBabyId(babyId, yyyymmdd);
		
	}
}
