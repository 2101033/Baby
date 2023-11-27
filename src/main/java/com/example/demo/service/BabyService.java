package com.example.demo.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

//import com.example.demo.entity.baby;
import com.example.demo.entity.user;
import com.example.demo.entity.weight;

public interface BabyService {
	//新規登録
	public void insertUser(String mail, String pass, String user_name,Boolean user_type
			);
	
	public void insertBaby(String user_mail,String baby_name,String birth,String sex,MultipartFile multipartFile)throws IOException;
	/**
	 * メールアドレスからユーザー取得
	 * @param mail
	 */
	public user getUser(String mail);
	
	/**
	 * メールアドレスとパスワードからユーザー取得
	 * passはmd5化されたものを渡してください
	 * @param mail
	 * @param pass
	 * @return
	 */
	public user getAuthUser(String mail, String pass);
	
	/**
	 * babyIdから日時と体重のmapを取得
	 * @param id
	 * @return
	 */
	public Map<LocalDateTime, Double> getWeightByBabyId(Integer id);
}
