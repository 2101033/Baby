package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.user;
import com.example.demo.form.UserNewRegisterForm;
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
	public UserNewRegisterForm setUpWordForm() {
		return new UserNewRegisterForm();
	}

	@ModelAttribute
	public BabyNewRegisterForm setUpBabyNewRegisterForm() {
		return new BabyNewRegisterForm();
	}

	@PostMapping("/OK")
	public String showLoginForm(Model model, UserNewRegisterForm userNewRegisterForm,
			BabyNewRegisterForm babyNewregisterForm) {
		babyNewregisterForm.setMail(userNewRegisterForm.getMail());
		service.insertBaby(babyNewregisterForm.getMail(), babyNewregisterForm.getBaby_name(),
				babyNewregisterForm.getBirth(),babyNewregisterForm.getSex(),
				babyNewregisterForm.getProfiel_image());

		// ログイン画面へ遷移。
		return "insertOK";
	}

	@GetMapping("newRegister")
	public String newRegisterView(@Validated UserNewRegisterForm babyNewRegisterForm, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "signup";
		}
		return "signup";
	}
	
//	@GetMapping("hostsignup")
//	public String hostsignupView(
//			Model model) {
//		
//		return "host-signup";
//	}

	/**
	 * ログイン画面
	 * 
	 * @author 本藤
	 * @return ログイン画面
	 */
	@GetMapping("login")
	public String loginView(LoginForm loginForm) {

		return "login";
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

	@PostMapping("newRegiRecord")
	public String newRegiRecordView(@Validated UserNewRegisterForm userNewRegisterForm,
									BindingResult bindingResult,Model model) {
		service.insertUser(
		userNewRegisterForm.getMail(), userNewRegisterForm.getPass(),
		userNewRegisterForm.getUser_name(), userNewRegisterForm.getUser_type());
		
		model.addAttribute("mail",userNewRegisterForm.getMail());
		model.addAttribute("password",userNewRegisterForm.getPass());
		model.addAttribute("user_name",userNewRegisterForm.getUser_name());
		model.addAttribute("recView",userNewRegisterForm.getUser_type());
		return "host-signup";
  }
  
	@GetMapping("weight")
	public String weightView() {
		return "weight";
	}
}
