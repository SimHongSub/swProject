package com.marketplace.test;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.marketplace.controller.GeneralController;
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
		generalController.showView();
	}
	
	@Test
	public void test2Register() {
		generalController.register("유닛테스트책", "저자", "출판사", "20200613", "Good", "12000");
	}
	
	@Test
	public void test3Search() {
		generalController.search("제목", "유닛");
	}
	
	@Test
	public void test4Modify() {
		generalController.modify(0, "유닛테스트책", "저자", "출판사", "20200613", "Good", "1200");
	}
	
	@Test
	public void test5Delete() {
		generalController.delete(0);
	}
}
