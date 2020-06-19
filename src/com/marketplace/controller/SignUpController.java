package com.marketplace.controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;

import com.marketplace.exception.SignUpException;
import com.marketplace.filesystem.FileProcessing;
import com.marketplace.model.User;
import com.marketplace.util.AES256Util;
import com.marketplace.view.SignUp;

/** 
 * Class responsible for sign up page backend logic.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
public class SignUpController {
	/**
	 * view - General view object.
	 * userList - List to saved user object.
	 * fp - FileProcessing object to read file.
	 */
	private SignUp view;
	private ArrayList<User> userList;
	private FileProcessing fp;
	
	public SignUpController() {
		view = new SignUp();
		userList = new ArrayList<User>();
		fp = new FileProcessing();
		
		fp.readUserFile("userInfo", userList);
	}
	
	/**
	 * Method showing the view.
	 * 
	 * @exception SignUpException
	 */
	public void showView() throws SignUpException{
		try {
			view.show();
		}catch (Exception e) {
			new SignUpException("Signup controller showView() method exception.");
		}
	}
	
	/**
	 * Method to enrollment user.
	 * 
	 * @param id - input user id.
	 * @param password - input user password.
	 * @param name - input user name.
	 * @param phoneNumber - input user phone number.
	 * @param email - input user email.
	 * @return HashMap<String, String>
	 * @exception SignUpException
	 */
	public HashMap<String, String> enrollment(String id, String password, String name, String phoneNumber, String email) throws SignUpException {
		HashMap<String, String> result = new HashMap<String, String>();
		
		try {
			if(id.equals("") || password.equals("") || name.equals("") || phoneNumber.equals("") || email.equals("")) {
				result.put("message", "모든 정보를 입력하셔야 합니다.");
			}else {
				for(int i=0; i<userList.size(); i++) {
					User user = userList.get(i);
					
					if(user.getId().equals(id)) {
						result.put("message", "이미 존재하는 아이디입니다.");
						
						return result;
					}
				}
				
				//password encryption
				String encryptedPassword = null;
				
				try {
					AES256Util aes = new AES256Util();
					try {
						encryptedPassword = aes.encrypt(password);
					} catch (GeneralSecurityException e1) {
						e1.printStackTrace();
					}
					
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				
				User user = new User(id, encryptedPassword, name, phoneNumber, email, "GENERAL", "activated");
				
				userList.add(user);
				
				fp.writeUserFile(userList, "userInfo");
				
				result.put("message", "success");
			}
			
			return result;
		}catch (Exception e) {
			new SignUpException("Signup controller enrollment() method exception.");
			
			return null;
		}
	}
}
