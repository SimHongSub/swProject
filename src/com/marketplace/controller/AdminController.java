package com.marketplace.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.marketplace.filesystem.FileProcessing;
import com.marketplace.model.Book;
import com.marketplace.model.User;
import com.marketplace.view.Admin;

public class AdminController {

	private Admin view;
	private ArrayList<User> userList;
	private ArrayList<Book> bookList;
	private FileProcessing fp;
	
	public AdminController() {
		view = new Admin();
		userList = new ArrayList<User>();
		bookList = new ArrayList<Book>();
		fp = new FileProcessing();
		
		fp.readUserFile("userInfo", userList);
		fp.readBookFile("bookInfo", bookList);
	}
	
	public void showView() {
		view.show(userList);
	}
	
	public User select(int index) {
		return userList.get(index);
	}
	
	public void modify(int index) {
		if(userList.get(index).getState().equals("activated")) {
			userList.get(index).setState("deactivated");
		}else {
			userList.get(index).setState("activated");
		}
		
		fp.writeUserFile(userList, "userInfo");
	}
	
	public void search(String searchItem, String searchText) {
		ArrayList<User> searchedUser = new ArrayList<User>();
		
		if(searchItem.equals("사용자ID")) {
			for(int i=0; i<userList.size(); i++) {
				User user = userList.get(i);
				
				if(user.getId().equals(searchText)) {
					searchedUser.add(user);
				}
			}
		}else {
			for(int i=0; i<userList.size(); i++) {
				User user = userList.get(i);
				
				if(user.getName().contains(searchText)) {
					searchedUser.add(user);
				}
			}
		}
		
		view.show(searchedUser);
	}
	
	public void delete(int index){
		User deletedUser = userList.get(index);
		
		String userId = deletedUser.getId();
		
		for(int i=0; i<userList.size(); i++) {
			User user = userList.get(i);
			
			if(userId.equals(user.getId())) {
				userList.remove(i);
				
				break;
			}
		}
		
		fp.writeUserFile(userList, "userInfo");
		
		for(int i=0; i<bookList.size(); i++) {
			Book book = bookList.get(i);
			
			if(userId.equals(book.getUserId())) {
				bookList.remove(i);
				i--;
			}
		}
		
		fp.writeBookFile(bookList, "bookInfo");
	}
	
	public HashMap<String, String> checkState(int index){
		HashMap<String, String> result = new HashMap<String, String>();
		User deletedUser = userList.get(index);
		
		String userState = deletedUser.getState();
		
		if(userState.equals("activated")) {
			result.put("message", "fail");
		}else {
			result.put("message", "success");
		}
		
		return result;
	}
}
