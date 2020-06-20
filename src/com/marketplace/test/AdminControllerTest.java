package com.marketplace.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.marketplace.controller.AdminController;
import com.marketplace.exception.AdminException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminControllerTest {

	AdminController adminController;
	
	public AdminControllerTest() {
		adminController = new AdminController();
	}
	
	@Test
	public void test1ShowView() {
		try {
			adminController.showView();
		} catch (AdminException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2Select() {
		try {
			assertEquals("unitTest", adminController.select(3).getId());
		} catch (AdminException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3Search() {
		try {
			adminController.search("사용자ID", "unitTest");
		} catch (AdminException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test4Modify() {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("message", "success");
		
		try {
			assertEquals(result, adminController.modify(3));
		} catch (AdminException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test5Delete() {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("message", "success");
		
		try {
			assertEquals(result, adminController.delete(3));
		} catch (AdminException e) {
			e.printStackTrace();
		}
	}
}
