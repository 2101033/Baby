package com.example.demo.service;

//import com.example.demo.entity.baby;
import com.example.demo.entity.user;

public interface BabyService {
	//新規登録
	public void insertUser(String mail, String pass, String user_name,Boolean user_type
			);
	
	public void insertBaby(String user_mail,String baby_name,String birth_year,String birth_mouth,String birth_day,
			String sex,String profiel_image);
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
}
