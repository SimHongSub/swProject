package com.marketplace.main;

import com.marketplace.controller.LoginController;
import com.marketplace.model.User;

public class MarketPlace {
	
	public static User my;

	public static void main(String[] args) {
		LoginController loginController = new LoginController();
		
		loginController.showView();
		/*char[] test = {'1', '2', '3'};
		
		loginController.loginCheck("admin", test);*/
	}
}