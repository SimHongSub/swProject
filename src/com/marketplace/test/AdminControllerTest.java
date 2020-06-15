package com.marketplace.test;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.marketplace.controller.AdminController;
import com.marketplace.model.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminControllerTest {

	AdminController adminController;
	
	public AdminControllerTest() {
		adminController = new AdminController();
	}
	
	@Test
	public void test1ShowView() {
		adminController.showView();
	}
	
	@Test
	public void test2Select() {
		assertEquals("unitTest", adminController.select(3).getId());
	}
	
	@Test
	public void test3Search() {
		adminController.search("사용자ID", "unitTest");
	}

	@Test
	public void test4CheckState() {
		adminController.checkState(3);
	}
	
	@Test
	public void test5Modify() {
		adminController.modify(3);
	}
	
	@Test
	public void test6Delete() {
		adminController.delete(3);
	}
}
