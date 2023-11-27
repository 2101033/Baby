package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.baby;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.entity.user;
import com.example.demo.entity.weight;
import com.example.demo.repository.BabyRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WeightRepository;

@Service
public class BabyServiceImpl implements BabyService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BabyRepository babyRepository;
	
	@Autowired
	WeightRepository weightRepository;
	
//	@Autowired
//	private BCryptPasswordEncoder bcpe;
	
	@Override
	public void insertBaby(String user_mail,String baby_name,String birth,
			String sex,MultipartFile profiel_image) throws IOException  {
		String UId = UUID.randomUUID().toString();
		
		
		user user = userRepository.getBabyUserId(user_mail);
		baby baby = new baby();
		baby.setBaby_id(null);
	    baby.setBaby_name(baby_name);
	    baby.setBirth(birth);
	    baby.setSex(sex);
	    baby.setProfiel_image(UId+profiel_image.getOriginalFilename());
	    baby.setUser_id(user.getUser_id());
	    
	    Path dst = Path.of("src/main/resources/static/images/babys/", UId + profiel_image.getOriginalFilename());
		Files.copy(profiel_image.getInputStream(), dst);
		
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

	@Override
	public Map<LocalDateTime, Double> getWeightByBabyId(Integer id) {
		Iterable<weight> weights = weightRepository.getAllWeightByBabyId(id);
		Map<LocalDateTime, Double> data = new HashMap<LocalDateTime, Double>();
		for (weight weight : weights) {
			data.put(weight.getWeight_date(), weight.getWeight());
		}
		return data;
	}
}
