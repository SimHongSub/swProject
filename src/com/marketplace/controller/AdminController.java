package com.marketplace.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.marketplace.exception.AdminException;
import com.marketplace.filesystem.FileProcessing;
import com.marketplace.model.Book;
import com.marketplace.model.User;
import com.marketplace.view.Admin;

/** 
 * Class responsible for admin page backend logic.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
public class AdminController{
	/**
	 * view - Admin view object.
	 * userList - List to saved user object.
	 * bookList - List to saved book object.
	 * fp - FileProcessing object to read file.
	 */
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
	
	/**
	 * Method showing the view.
	 */
	public void showView() throws AdminException{
		try {
			view.show(userList);
		}catch (Exception e) {
			new AdminException("Admin Controller showView() method exception.");
		}
	}
	
	/**
	 * Method to return the selected User.
	 * 
	 * @param index - userList index.
	 * @return User
	 */
	public User select(int index) throws AdminException {
		try {
			return userList.get(index);
		}catch (Exception e) {
			new AdminException("Admin Controller select() method exception.");
			
			return null;
		}
	}
	
	/**
	 * Method to modify the user state.
	 * 
	 * @param index - userList index.
	 */
	public void modify(int index) throws AdminException {
		try {
			if(userList.get(index).getState().equals("activated")) {
				userList.get(index).setState("deactivated");
			}else {
				userList.get(index).setState("activated");
			}
			
			fp.writeUserFile(userList, "userInfo");
		}catch (Exception e) {
			new AdminException("Admin Controller modify() method exception.");
		}
		
	}
	
	/**
	 * Method to search for users related to input information.
	 * 
	 * @param searchItem - search item information.
	 * @param searchText - search text information.
	 */
	public void search(String searchItem, String searchText) throws AdminException {
		ArrayList<User> searchedUser = new ArrayList<User>();
		try {
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
		}catch (Exception e) {
			new AdminException("Admin Controller search() method exception.");
		}
		
	}
	
	/**
	 * Method to delete selected user.
	 * 
	 * @param index - userList index.
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> delete(int index) throws AdminException{
		HashMap<String, String> result = new HashMap<String, String>();
		User deletedUser = userList.get(index);
		
		try {
			if(deletedUser.getState().equals("deactivated")) {
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
				
				result.put("message", "success");
			}else {
				result.put("message", "fail");
			}
			
			return result;
		}catch (Exception e) {
			new AdminException("Admin Controller delete() method exception.");
			
			return null;
		}
		
		
	}
}
