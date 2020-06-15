package com.marketplace.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.marketplace.controller.MainController;
import com.marketplace.main.MarketPlace;
import com.marketplace.model.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainControllerTest {
	MainController mainController;
	
	public MainControllerTest() {
		mainController = new MainController();
		User user = new User("unitTest", "123", "유닛테스트", "55555555", "unit@test.com", "GENERAL", "activated");
		
		MarketPlace.my = user;
	}
	
	@Test
	public void test1ShowView() {
		mainController.showView();
	}
	
	@Test
	public void test2Purchase() {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("message", "구매자 unitTest E-mail : unit@test.com\n"
				+ "판매자 testid E-mail : test@naver.com\n메일이 발송되었습니다.");
		
		assertEquals(result, mainController.purchase(1));
	}
	
	@Test
	public void test3Delete() {
		mainController.delete(0);
	}

}
