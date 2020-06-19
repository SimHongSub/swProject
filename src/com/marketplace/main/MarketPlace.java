package com.marketplace.main;

import com.marketplace.controller.LoginController;
import com.marketplace.exception.LoginException;
import com.marketplace.model.User;

/** 
 * Class responsible for marketplace system start.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
public class MarketPlace {
	/**
	 * my - login user object
	 */
	public static User my;

	public static void main(String[] args) {
		LoginController loginController = new LoginController();
		
		try {
			loginController.showView();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}
}

