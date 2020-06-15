package com.marketplace.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.marketplace.controller.LoginController;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginControllerTest {
	
	LoginController loginController;
	
	public LoginControllerTest() {
		loginController = new LoginController();
	}
	
	@Test
	public void test1ShowView() {
		loginController.showView();
	}
	
	@Test
	public void test2LoginCheck() {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("message", "success");
		
		assertEquals(result, loginController.loginCheck("admin", "nayana"));
	}

}
