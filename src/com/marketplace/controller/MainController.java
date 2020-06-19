package com.marketplace.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.marketplace.exception.MainException;
import com.marketplace.filesystem.FileProcessing;
import com.marketplace.model.Book;
import com.marketplace.model.User;
import com.marketplace.util.EmailUtil;
import com.marketplace.view.Main;

/** 
 * Class responsible for main page backend logic.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
public class MainController {
	/**
	 * view - General view object.
	 * userList - List to saved user object.
	 * bookList - List to saved book object.
	 * fp - FileProcessing object to read file.
	 */
	private Main view;
	private ArrayList<Book> bookList;
	private ArrayList<User> userList;
	private FileProcessing fp;
	
	public MainController() {
		view = new Main();
		bookList = new ArrayList<Book>();
		userList = new ArrayList<User>();
		fp = new FileProcessing();
		
		fp.readBookFile("bookInfo", bookList);
		fp.readUserFile("userInfo", userList);
	}
	
	/**
	 * Method showing the view.
	 * 
	 * @exception MainException
	 */
	public void showView() throws MainException{
		try {
			view.show(bookList);
		}catch (Exception e) {
			new MainException("Main controller showView() method exception.");
		}
	}
	
	/**
	 * Method to search for books related to input information.
	 * 
	 * @param searchItem - search item information.
	 * @param searchText - search text information.
	 * @exception MainException
	 */
	public void search(String searchItem, String searchText) throws MainException{
		ArrayList<Book> searchBooks = new ArrayList<Book>();
		
		try {
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
			}else if(searchItem.equals("출판사")) {
				for(int i=0; i<bookList.size(); i++) {
					Book book = bookList.get(i);
					
					if(book.getPublisher().equals(searchText)) {
						searchBooks.add(book);
					}
				}
			}else if(searchItem.equals("출판년도")) {
				for(int i=0; i<bookList.size(); i++) {
					Book book = bookList.get(i);
					
					if(book.getDate().equals(searchText)) {
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
		}catch (Exception e) {
			new MainException("Main controller search() method exception.");
		}
	}
	
	/**
	 * Method to purchase book.
	 * 
	 * @param index - bookList index.
	 * @return HashMap<String, String>
	 * @exception MainException
	 */
	public HashMap<String, String> purchase(int index) throws MainException{
		HashMap<String, String> result = new HashMap<String, String>();
		
		try {
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
		}catch (Exception e) {
			new MainException("Main controller purchase() method exception.");
			
			return null;
		}
	}
	
	/**
	 * Method to delete selected book.
	 * 
	 * @param index - bookList index.
	 * @exception MainException
	 */
	public void delete(int index) throws MainException{
		try {
			bookList.remove(index);
			
			fp.writeBookFile(bookList, "bookInfo");
		}catch (Exception e) {
			new MainException("Main controller delete() method exception.");
		}
	}
}
