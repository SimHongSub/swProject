package com.marketplace.main;

import com.marketplace.controller.LoginController;

public class MarketPlace {

	public static void main(String[] args) {
		LoginController loginController = new LoginController();
		
		loginController.showView();
	}
}