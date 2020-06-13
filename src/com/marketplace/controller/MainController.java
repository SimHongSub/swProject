package com.marketplace.controller;

import com.marketplace.view.Main;

public class MainController {

	private Main view;
	
	public MainController() {
		view = new Main();
	}
	
	public void showview() {
		view.show();
	}
}
