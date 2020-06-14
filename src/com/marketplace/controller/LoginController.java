package com.marketplace.controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;

import com.marketplace.filesystem.FileProcessing;
import com.marketplace.main.MarketPlace;
import com.marketplace.model.User;
import com.marketplace.util.AES256Util;
import com.marketplace.view.Login;

public class LoginController {
	private Login view;
	private ArrayList<User> userList;
	private FileProcessing fp;
	
	public LoginController() {
		view = new Login();
		userList = new ArrayList<User>();
		fp = new FileProcessing();
		
		fp.readUserFile("userInfo", userList);
	}
	
	public void showView() {
		view.show();
	}
	
	public HashMap<String, String> loginCheck(String id, char[] pw) {
		HashMap<String, String> result = new HashMap<String, String>();
		
		for(int i=0; i<userList.size(); i++) {
			User user = userList.get(i);
			String decryptedPassword = null;
			
			if(id.equals(user.getId())){
				try {
					AES256Util aesUtil = new AES256Util();
					
					try {
						decryptedPassword = aesUtil.decrypt(user.getPassword());
					} catch (GeneralSecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(decryptedPassword.equals(new String(pw))) {
					result.put("message", "success");
					
					MarketPlace.my = user;
					
					return result;
				}else {
					result.put("message", "비밀번호를 잘못 입력하셨습니다.");
					
					return result;
				}
			}
		}
		
		result.put("message", "없는 아이디입니다.");
		
		return result;
	}

}
