package com.marketplace.controller;

import com.marketplace.view.Admin;

public class AdminController {

	private Admin view;
	
	public AdminController() {
		view = new Admin();
	}
	
	public void showView() {
		view.show();
	}
}
