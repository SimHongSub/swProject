package com.marketplace.controller;

import com.marketplace.view.Login;

public class LoginController {
	private Login view;
	
	public LoginController() {
		view = new Login();
	}
	
	public void showView() {
		view.show();
	}
	
	public void loginCheck() {
		
	}

}
