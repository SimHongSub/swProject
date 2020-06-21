package com.marketplace.controller;

import java.util.ArrayList;

import com.marketplace.filesystem.FileProcessing;
import com.marketplace.model.Book;
import com.marketplace.model.User;

/** 
 * Class that contains common property information to all controllers.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
public class Controller {
	/**
	 * userList - List to saved user object.
	 * bookList - List to saved book object.
	 * fp - FileProcessing object to read file.
	 */
	protected ArrayList<User> userList;
	protected ArrayList<Book> bookList;
	protected FileProcessing fp;
	
	public Controller() {
		userList = new ArrayList<User>();
		bookList = new ArrayList<Book>();
		fp = new FileProcessing();
		
		fp.readUserFile("userInfo", userList);
		fp.readBookFile("bookInfo", bookList);
	}
}
