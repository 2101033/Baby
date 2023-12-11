package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.baby;
import com.example.demo.entity.invitation;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.entity.user;
import com.example.demo.entity.weight;
import com.example.demo.repository.BabyRepository;
import com.example.demo.repository.InvitationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WeightRepository;

@Service
public class BabyServiceImpl implements BabyService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BabyRepository babyRepository;
	
	@Autowired
	InvitationRepository invitationRepository;

	@Autowired
	WeightRepository weightRepository;
	
//	@Autowired
//	private BCryptPasswordEncoder bcpe;
	
	@Override//ユーザー新規登録
	public void insertUser(String mail, String pass, String user_name,Boolean user_type) {
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
	public Optional<invitation> hostInvitation(Integer user_id) {
		return invitationRepository.invitationHost_User_Id(user_id);
	}
	
	@Override
	public void createInvitation(Integer user_id, String md5Hex) {
		invitationRepository.save(user_id,md5Hex);
	}
	
	@Override//閲覧側新規登録_招待コード
	public invitation viewInvitaion(String invitation_code) {
		return invitationRepository.invitationCheck(invitation_code);
	}
	
	@Override//赤ちゃん新規登録
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
	
	
//	@SuppressWarnings("unchecked")
	@Override//赤ちゃん情報を取得
	public baby babyfindAll(String usermail) {
		user user = userRepository.getUserByEmail(usermail);
		Integer user_id = user.getUser_id();
		baby baby = babyRepository.getBabyByfindAll(user_id);
		return baby;
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
	public Map<LocalDateTime, Double> getWeightByBabyId(Integer id, String yyyymm) {
		Iterable<weight> weights = weightRepository.getAllWeightByBabyId(id, yyyymm);
		Map<LocalDateTime, Double> data = new HashMap<LocalDateTime, Double>();
		for (weight weight : weights) {
			data.put(weight.getWeight_date(), weight.getWeight());
		}
		return data;
	}
}
