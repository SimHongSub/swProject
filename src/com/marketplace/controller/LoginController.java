package com.marketplace.controller;

import com.marketplace.view.Login;

public class LoginController {
	
	private static Login view = new Login();
	
	public LoginController() {
		
	}
	
	public static void showView() {
		view.show();
	}
	
	public static void loginClick() {
		
	}
	
	public static void signUpClick() {
		view.close();
		SignUpController.showView();
	}

}
