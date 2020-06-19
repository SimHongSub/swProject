package com.marketplace.controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;

import com.marketplace.exception.LoginException;
import com.marketplace.filesystem.FileProcessing;
import com.marketplace.main.MarketPlace;
import com.marketplace.model.User;
import com.marketplace.util.AES256Util;
import com.marketplace.view.Login;

/** 
 * Class responsible for login page backend logic.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
public class LoginController {
	/**
	 * view - General view object.
	 * userList - List to saved user object.
	 * fp - FileProcessing object to read file.
	 */
	private Login view;
	private ArrayList<User> userList;
	private FileProcessing fp;
	
	public LoginController() {
		view = new Login();
		userList = new ArrayList<User>();
		fp = new FileProcessing();
		
		fp.readUserFile("userInfo", userList);
	}
	
	/**
	 * Method showing the view.
	 * 
	 * @exception LoginException
	 */
	public void showView() throws LoginException{
		try {
			view.show();
		}catch (Exception e) {
			new LoginException("Login controller showView() method exception.");
		}	
	}
	
	/**
	 * Method to check user login information.
	 * 
	 * @param id - input user id information.
	 * @param pw - input user password information.
	 * @return HashMap<String, String>
	 * @exception LoginException
	 */
	public HashMap<String, String> loginCheck(String id, String pw) throws LoginException{
		HashMap<String, String> result = new HashMap<String, String>();
		
		try {
			for(int i=0; i<userList.size(); i++) {
				User user = userList.get(i);
				String decryptedPassword = null;
				
				if(id.equals(user.getId())){
					try {
						AES256Util aesUtil = new AES256Util();
						
						try {
							decryptedPassword = aesUtil.decrypt(user.getPassword());
						} catch (GeneralSecurityException e) {
							e.printStackTrace();
						}
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					
					if(decryptedPassword.equals(pw)) {
						if(user.getState().equals("activated")) {
							result.put("message", "success");
							MarketPlace.my = user;
							
							return result;
						}else {
							result.put("message", "관리자로부터 로그인이 제한되었습니다.");
							
							return result;
						}
					}else {
						result.put("message", "비밀번호를 잘못 입력하셨습니다.");
						
						return result;
					}
				}
			}
			
			result.put("message", "없는 아이디입니다.");
			
			return result;
		}catch (Exception e) {
			new LoginException("Login controller loginCheck() method exception.");
			
			return null;
		}
	}
}
