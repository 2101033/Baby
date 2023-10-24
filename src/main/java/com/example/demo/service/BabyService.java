package com.example.demo.service;

import com.example.demo.entity.user;

public interface BabyService {
	//新規登録
	public void insertUser(String mail, String pass, String user_name,Boolean user_type);
	
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
