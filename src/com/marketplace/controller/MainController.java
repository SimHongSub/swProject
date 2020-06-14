package com.marketplace.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.marketplace.filesystem.FileProcessing;
import com.marketplace.model.Book;
import com.marketplace.model.User;
import com.marketplace.util.EmailUtil;
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
	
	public void showView() {
		view.show(bookList);
	}
	
	public void search(String searchItem, String searchText) {
		ArrayList<Book> searchBooks = new ArrayList<Book>();
		
		if(searchItem.equals("ISBN번호")){
			for(int i=0; i<bookList.size(); i++) {
				Book book = bookList.get(i);
				
				if(book.getIsbn().equals(searchText)) {
					searchBooks.add(book);
					
					break;
				}
			}
		}else if(searchItem.equals("제목")) {
			for(int i=0; i<bookList.size(); i++) {
				Book book = bookList.get(i);
				
				if(book.getTitle().contains(searchText)) {
					searchBooks.add(book);
				}
			}
		}else if(searchItem.equals("저자")) {
			for(int i=0; i<bookList.size(); i++) {
				Book book = bookList.get(i);
				
				if(book.getAuthor().equals(searchText)) {
					searchBooks.add(book);
				}
			}
		}else {
			for(int i=0; i<bookList.size(); i++) {
				Book book = bookList.get(i);
				
				if(book.getUserId().equals(searchText)) {
					searchBooks.add(book);
				}
			}
		}
		
		view.show(searchBooks);
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
		
		EmailUtil emailUtil = new EmailUtil();
		
		result.put("message", emailUtil.send(sellerId, sellerEmail));
		
		return result;
	}
	
	public void delete(int index) {
		bookList.remove(index);
		
		fp.writeBookFile(bookList, "bookInfo");
	}
}
