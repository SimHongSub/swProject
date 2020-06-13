package com.marketplace.controller;

import com.marketplace.view.General;

public class GeneralController {
	
	private General view;
	
	public GeneralController() {
		view = new General();
	}
	
	public void showView() {
		view.show();
	}

}
