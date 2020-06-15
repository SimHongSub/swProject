package com.marketplace.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.marketplace.filesystem.FileProcessing;
import com.marketplace.model.User;
import com.marketplace.view.SignUp;

public class SignUpController {
	
	private SignUp view;
	private ArrayList<User> userList;
	private FileProcessing fp;
	
	public SignUpController() {
		view = new SignUp();
		userList = new ArrayList<User>();
		fp = new FileProcessing();
		
		fp.readUserFile("userInfo", userList);
	}
	
	public void showView() {
		view.show();
	}
	
	public HashMap<String, String> enrollment(String id, String password, String name, String phoneNumber, String email) {
		HashMap<String, String> result = new HashMap<String, String>();
		
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
			
			User user = new User(id, password, name, phoneNumber, email, "GENERAL", "activated");
			
			userList.add(user);
			
			fp.writeUserFile(userList, "userInfo");
			
			result.put("message", "success");
		}
		
		return result;
	}

}
