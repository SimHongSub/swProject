package com.marketplace.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;

import com.marketplace.filesystem.FileProcessing;
import com.marketplace.model.User;
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
			
			if(id.equals(user.getId())){
				if(user.getPassword().equals(new String(pw))) {
					result.put("message", "success");
					
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
