package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.BabyNewRegisterForm;
import com.example.demo.service.BabyService;




@Controller
public class BabyController {
	@Autowired
	BabyService service;
	
	//バリデーションをつける場合に必要になってくる
	@ModelAttribute
    public BabyNewRegisterForm setUpWordForm() {
        return new BabyNewRegisterForm();
    }
	
	@RequestMapping("/OK")
    public String showLoginForm(Model model,BabyNewRegisterForm babyNewRegisterForm) {
		service.insertUser(babyNewRegisterForm.getMail(),babyNewRegisterForm.getPass(),babyNewRegisterForm.getUser_name(),babyNewRegisterForm.getUser_type());
        //ログイン画面へ遷移。
        return "insertOK";
    }
	
	@GetMapping("newRegister")
	public String newRegisterView(@Validated BabyNewRegisterForm babyNewRegisterForm,BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			return "NewRegister";
		}
		return "NewRegister";
	}
}