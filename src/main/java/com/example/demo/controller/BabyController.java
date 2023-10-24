package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.user;
import com.example.demo.form.BabyNewRegisterForm;
import com.example.demo.form.LoginForm;
import com.example.demo.service.BabyService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BabyController {
	@Autowired
	BabyService service;
	
	@Autowired
	HttpSession session;

	// バリデーションをつける場合に必要になってくる
	@ModelAttribute
	public BabyNewRegisterForm setUpWordForm() {
		return new BabyNewRegisterForm();
	}

	@RequestMapping("/OK")
	public String showLoginForm(Model model, BabyNewRegisterForm babyNewRegisterForm) {
		service.insertUser(babyNewRegisterForm.getMail(), babyNewRegisterForm.getPass(),
				babyNewRegisterForm.getUser_name(), babyNewRegisterForm.getUser_type());
		// ログイン画面へ遷移。
		return "insertOK";
	}

	@GetMapping("newRegister")
	public String newRegisterView(@Validated BabyNewRegisterForm babyNewRegisterForm, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "NewRegister";
		}
		return "NewRegister";
	}

	/**
	 * ログイン画面
	 * 
	 * @author 本藤
	 * @return ログイン画面
	 */
	@GetMapping("login")
	public String loginView(LoginForm loginForm) {

		return "login-dev";
	}

	/**
	 * ログイン処理を行う
	 * 
	 * @author 本藤
	 * @return 成功した場合はindexへ遷移し、失敗した場合はloginへリダイレクトする。
	 */
	@PostMapping("index")
	public String login(LoginForm loginForm, RedirectAttributes redirectAttributes, Model model) {
		
		user user = service.getAuthUser(loginForm.getEmail(), loginForm.getPassword());
//		model.addAttribute("user", user);

		// ログイン失敗
		if (user == null) {
			redirectAttributes.addFlashAttribute("errMsg", "ログイン失敗");
			return "redirect:login";
		}

		session.setAttribute("user", user);
		return "index";
	}

}
