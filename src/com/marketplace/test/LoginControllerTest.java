package com.marketplace.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.marketplace.controller.LoginController;
import com.marketplace.exception.LoginException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginControllerTest {
	
	LoginController loginController;
	
	public LoginControllerTest() {
		loginController = new LoginController();
	}
	
	@Test
	public void test1ShowView() {
		try {
			loginController.showView();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2LoginCheck() {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("message", "success");
		
		try {
			assertEquals(result, loginController.loginCheck("admin", "nayana"));
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}

}
