package com.marketplace.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.marketplace.controller.SignUpController;
import com.marketplace.exception.SignUpException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpControllerTest {
	
	SignUpController signUpController;
	
	public SignUpControllerTest() {
		signUpController = new SignUpController();
	}
	
	@Test
	public void test1ShowView() {
		try {
			signUpController.showView();
		} catch (SignUpException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2Enrollment() {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("message", "success");
		
		try {
			assertEquals(result, signUpController.enrollment("unitTest", "123", "유닛테스트", "55555555", "unit@test.com"));
		} catch (SignUpException e) {
			e.printStackTrace();
		}
	}

}
