package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
//import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.baby;
import com.example.demo.entity.invitation;
import com.example.demo.entity.user;
import com.example.demo.form.BabyNewRegisterForm;
import com.example.demo.form.InvNewRegisterForm;
import com.example.demo.form.LoginForm;
import com.example.demo.form.UserNewRegisterForm;
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
	public String showLoginForm(@Validated BabyNewRegisterForm babyNewregisterForm, BindingResult bindingResult,
								Model model, UserNewRegisterForm userNewRegisterForm) throws IOException  {
		
		if (bindingResult.hasErrors()) {
			return "host-signup";
		}
		
		babyNewregisterForm.setMail(userNewRegisterForm.getMail());
		service.insertBaby(babyNewregisterForm.getMail(), babyNewregisterForm.getBaby_name(),
				babyNewregisterForm.getBirth(),babyNewregisterForm.getSex(),
				babyNewregisterForm.getProfiel_image());

		// ログイン画面へ遷移。
		return "insertOK";
	}
	
	//新規登録画面へ
	@GetMapping("newRegister")
	public String newRegisterView(UserNewRegisterForm babyNewRegisterForm, BindingResult bindingResult,
			Model model) {
		return "signup";
	}
	
	@GetMapping("view-home")
	public String testHomeView(Model model) {
		//セッションを取得
		Object user = session.getAttribute("user");
		
		// 現在の日付を取得する
        LocalDate currentDate = LocalDate.now();
		
		// データフォーマットを指定
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		if (user != null) {
	        String email = ((user)user).getUser_mail(); // ユーザーオブジェクトからメールアドレスを取得
	        baby list = service.babyfindAll(email); // メールアドレスを渡してbabyテーブルの情報取得
	        LocalDate birthDate = LocalDate.parse(list.getBirth(), formatter);//listから取得した誕生日をLocalDateに変換する
	        
	     //  生後何日かを計算する
//	        long after_birth =Period.between(birthDate, currentDate).getDays();
	        long after_birth = ChronoUnit.DAYS.between(birthDate, currentDate);
	        
	     //何歳何カ月何日目かを計算する
	        Period period = Period.between(birthDate, currentDate);
	        int year = period.getYears();
	        int month = period.getMonths();
	        int day = period.getDays();
	      
	      
	        model.addAttribute("babyList", list);
	        model.addAttribute("afterBirth",after_birth);
	        model.addAttribute("birthYear", year);
	        model.addAttribute("birthMonth", month);
	        model.addAttribute("birthDay", day);
	    } else {
	        // ログインしていない場合の処理
	        return "redirect:login";
	    }
		return "view-home";
	}
	//日記記録画面へ
	@GetMapping("diary_record")
	public String diary_recordView(Model model) {
		// 現在の日時を取得
		LocalDateTime datetime = LocalDateTime.now();
		
		//表示データフォーマットを指定
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd(E)");
		
		 // フォーマットして文字列に変換
		String formattedDate = datetime.format(formatter);
		
		//セッションを取得
		Object user = session.getAttribute("user");
		
		if (user != null) {
	        String email = ((user)user).getUser_mail(); // ユーザーオブジェクトからメールアドレスを取得
	        model.addAttribute("nowtime", formattedDate);
	        baby list = service.babyfindAll(email); // メールアドレスを渡してbabyテーブルの情報取得
	        model.addAttribute("babyList", list);
	    } else {
	        // ログインしていない場合の処理
	        return "redirect:login";
	    }
		return "diary_record";
	}
	
	//プロフィール画面へ
	@GetMapping("profile")
	public String profileView(Model model) {
		
		//セッションを取得
		Object user = session.getAttribute("user");
		
		if (user != null) {
	        String email = ((user)user).getUser_mail(); // ユーザーオブジェクトからメールアドレスを取得
	        user list = service.getUser(email); // メールアドレスを渡してbabyテーブルの情報取得
	        model.addAttribute("userList", list);
	    } else {
	        // ログインしていない場合の処理
	        return "redirect:login";
	    }
		return "profile";
	}
	
	//赤ちゃん情報画面へ
		@GetMapping("babyinfo")
		public String babyinfoView(Model model) {
			
			//セッションを取得
			Object user = session.getAttribute("user");
			
			if (user != null) {
		        String email = ((user)user).getUser_mail(); // ユーザーオブジェクトからメールアドレスを取得
		        baby list = service.babyfindAll(email); // メールアドレスを渡してbabyテーブルの情報取得
		        model.addAttribute("babyList", list);
		    } else {
		        // ログインしていない場合の処理
		        return "redirect:login";
		    }
			return "baby_info";
		}
	
	
	@GetMapping("host_signup")
	public String hostsignupView(
			Model model) {
		
		return "host-signup";
	}
	
	@GetMapping("view_signup")
	public String viewsignupView(InvNewRegisterForm invNewRegisterForm, BindingResult bindingResult,
			Model model) {
		
		return "view-signup";
	}
	
	//閲覧者側登録画面（招待コード）
	@PostMapping("/ok")
	public String showLoginForm(@Validated InvNewRegisterForm invNewregisterForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes,Model model, UserNewRegisterForm userNewRegisterForm) throws IOException  {
		
		if (bindingResult.hasErrors()) {
			return "view-signup";
		}
		
		//招待コードが合っていたら...という処理を書く
		invitation invitation_code = service.viewInvitaion(invNewregisterForm.getCode());
		if(invitation_code != null) {
			service.insertUser(invNewregisterForm.getMail(),invNewregisterForm.getPassword(),
								invNewregisterForm.getUser_name(),invNewregisterForm.getRecView());
		}else{
			redirectAttributes.addFlashAttribute("errMsg", "招待コードが間違っています。");
			return "redirect:view-signup";
		}
		
		// ログイン画面へ遷移。
		return "insertOK";
	}

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
	public String login(@Validated LoginForm loginForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "login";
		}
		
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
			InvNewRegisterForm invNewRegisterForm,BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			return "signup";
		}
		//ユーザタイプが閲覧側(1)だったら
		if(userNewRegisterForm.getUser_type() == true) {
			
			model.addAttribute("mail",userNewRegisterForm.getMail());
			model.addAttribute("password",userNewRegisterForm.getPass());
			model.addAttribute("user_name",userNewRegisterForm.getUser_name());
			model.addAttribute("recView",userNewRegisterForm.getUser_type());
			return "view-signup";
		}
	
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
	
	@GetMapping("uploadtest")
	public String uploadTest() {
		return "upload-test";
	}

	@PostMapping("/upload")
	public String upload(@RequestParam MultipartFile file) throws IOException {
		Path dst = Path.of("src/main/resources/static/images/babys/", UUID.randomUUID().toString() + file.getOriginalFilename());
		Files.copy(file.getInputStream(), dst);
		return "redirect:/uploadtest";
	}
	

	@GetMapping("viewhome")
	public String viewHome() {
		return "view-home";
	}
	
	@GetMapping("diary_insert")
	public String diary_insert() {
		return "diary_insert";
	}
	
	@GetMapping("diary")
	public String diary() {
		return "diary";
	}
	
	@GetMapping("diary_kalendar")
	public String diary_kalendar() {
		return "diary_kalendar";
	}
	
	@GetMapping("weightInsert")
	public String weightInsert() {
		return "weightInsert";
	}
}
