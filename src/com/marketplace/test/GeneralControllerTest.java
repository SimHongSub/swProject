package com.marketplace.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.marketplace.controller.GeneralController;
import com.marketplace.exception.GeneralException;
import com.marketplace.main.MarketPlace;
import com.marketplace.model.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneralControllerTest {

	GeneralController generalController;
	
	public GeneralControllerTest() {
		generalController = new GeneralController();
		User user = new User("unitTest", "123", "유닛테스트", "55555555", "unit@test.com", "GENERAL", "activated");
		
		MarketPlace.my = user;
	}
	
	@Test
	public void test1ShowView() {
		try {
			generalController.showView();
		} catch (GeneralException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2Register() {
		try {
			generalController.register("유닛테스트책", "저자", "출판사", "20200613", "Good", "12000");
		} catch (GeneralException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3Search() {
		try {
			generalController.search("제목", "유닛");
		} catch (GeneralException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test4Modify() {
		try {
			generalController.modify(0, "유닛테스트책", "저자", "출판사", "20200613", "Good", "1200");
		} catch (GeneralException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test5Delete() {
		try {
			generalController.delete(0);
		} catch (GeneralException e) {
			e.printStackTrace();
		}
	}
}
