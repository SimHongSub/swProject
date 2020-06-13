package com.marketplace.controller;

import java.util.ArrayList;

import com.marketplace.filesystem.FileProcessing;
import com.marketplace.model.Book;
import com.marketplace.view.Main;

public class MainController {

	private Main view;
	private ArrayList<Book> bookList;
	FileProcessing fp;
	
	public MainController() {
		view = new Main();
		bookList = new ArrayList<Book>();
		fp = new FileProcessing();
		
		fp.readBookFile("bookInfo", bookList);
	}
	
	public void showview() {
		view.show(bookList);
	}
}
