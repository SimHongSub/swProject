package com.marketplace.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.marketplace.filesystem.FileProcessing;
import com.marketplace.main.MarketPlace;
import com.marketplace.model.Book;
import com.marketplace.model.User;
import com.marketplace.view.Main;

public class MainController {

	private Main view;
	private ArrayList<Book> bookList;
	private ArrayList<User> userList;
	FileProcessing fp;
	
	public MainController() {
		view = new Main();
		bookList = new ArrayList<Book>();
		userList = new ArrayList<User>();
		fp = new FileProcessing();
		
		fp.readBookFile("bookInfo", bookList);
		fp.readUserFile("userInfo", userList);
	}
	
	public void showview() {
		view.show(bookList);
	}
	
	public HashMap<String, String> purchase(int index){
		HashMap<String, String> result = new HashMap<String, String>();
		
		Book book = bookList.get(index);
		
		String sellerId = book.getUserId();
		String sellerEmail = null;
		for(int i=0; i<userList.size(); i++) {
			User user = userList.get(i);
			
			if(user.getId().equals(sellerId)) {
				sellerEmail = user.getEmail();
				
				break;
			}
		}
		
		result.put("message", "구매자 " + MarketPlace.my.getId() + " E-mail : " + MarketPlace.my.getEmail() + "\n"
				+ "판매자 " + sellerId + " E-mail : " + sellerEmail + "\n메일이 발송되었습니다.");
		
		return result;
	}
}
