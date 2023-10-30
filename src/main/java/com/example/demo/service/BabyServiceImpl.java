package com.example.demo.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.baby;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.entity.user;
import com.example.demo.repository.BabyRepository;
import com.example.demo.repository.UserRepository;

@Service
public class BabyServiceImpl implements BabyService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BabyRepository babyRepository;
	
//	@Autowired
//	private BCryptPasswordEncoder bcpe;
	
	@Override
	public void insertBaby(String user_mail,String baby_name,String birth,
			String sex,String profiel_image) {
		user user = userRepository.getBabyUserId(user_mail);
		baby baby = new baby();
		baby.setBaby_id(null);
	    baby.setBaby_name(baby_name);
	    baby.setBirth(birth);
	    baby.setSex(sex);
	    baby.setProfiel_image(profiel_image);
	    baby.setUser_id(user.getUser_id());
	    
	    babyRepository.save(baby);
	}
	
	@Override
	public void insertUser(String mail, String pass, String user_name,Boolean user_type
			) {
		// パスワードをハッシュ化する
//	    String encodedPassword = bcpe.encode(pass);
	    
	    // ユーザー情報を作成する
	    user user = new user();
	    user.setUser_mail(mail);
	    user.setUser_pass(DigestUtils.md5Hex(pass));
	    user.setUser_name(user_name);
	    user.setUser_type(user_type);
	    
	    
	    
	    

	    // ユーザー情報を保存する
	    userRepository.save(user);
	    
	}

	@Override
	public user getUser(String mail) {
		user user = userRepository.getUserByEmail(mail);
		return user;
	}

	@Override
	public user getAuthUser(String mail, String pass) {
		user user = userRepository.getUserByEmailAndPass(mail, DigestUtils.md5Hex(pass));
		return user;
	}
}
