package com.marketplace.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.marketplace.exception.GeneralException;
import com.marketplace.filesystem.FileProcessing;
import com.marketplace.main.MarketPlace;
import com.marketplace.model.Book;
import com.marketplace.util.ISBNUtil;
import com.marketplace.view.General;

/** 
 * Class responsible for general user page backend logic.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
public class GeneralController {
	/**
	 * view - General view object.
	 * userList - List to saved user object.
	 * bookList - List to saved book object.
	 * fp - FileProcessing object to read file.
	 */
	private General view;
	private ArrayList<Book> bookList;
	private ArrayList<Book> myBooks;
	private FileProcessing fp;
	
	public GeneralController() {
		view = new General();
		bookList = new ArrayList<Book>();
		myBooks = new ArrayList<Book>();
		fp = new FileProcessing();
		
		fp.readBookFile("bookInfo", bookList);
		
		for(int i=0; i<bookList.size(); i++) {
			Book book = bookList.get(i);
			
			if(book.getUserId().equals(MarketPlace.my.getId())) {
				myBooks.add(book);
			}
		}
	}
	
	/**
	 * Method showing the view.
	 * 
	 * @exception GeneralException
	 */
	public void showView() throws GeneralException{
		try {
			view.show(myBooks);
		}catch (Exception e) {
			new GeneralException("General controller showView() method exception.");
		}
		
	}
	
	/**
	 * Method to search for books related to input information.
	 * 
	 * @param searchItem - search item information.
	 * @param searchText - search text information.
	 * @exception GeneralException
	 */
	public void search(String searchItem, String searchText) throws GeneralException {
		ArrayList<Book> searchBooks = new ArrayList<Book>();
		
		try {
			if(searchItem.equals("ISBN번호")){
				for(int i=0; i<myBooks.size(); i++) {
					Book book = myBooks.get(i);
					
					if(book.getIsbn().equals(searchText)) {
						searchBooks.add(book);
						
						break;
					}
				}
			}else if(searchItem.equals("제목")) {
				for(int i=0; i<myBooks.size(); i++) {
					Book book = myBooks.get(i);
					
					if(book.getTitle().contains(searchText)) {
						searchBooks.add(book);
					}
				}
			}else {
				for(int i=0; i<myBooks.size(); i++) {
					Book book = myBooks.get(i);
					
					if(book.getAuthor().equals(searchText)) {
						searchBooks.add(book);
					}
				}
			}
			
			view.show(searchBooks);
		}catch (Exception e) {
			new GeneralException("General controller search() method exception.");
		}	
	}
	
	/**
	 * Method to register book information.
	 * 
	 * @param title - input book title information.
	 * @param author - input book author information.
	 * @param publisher - input book publisher information.
	 * @param date - input book date information.
	 * @param state - input book state information.
	 * @param price - input book price information.
	 * @return HashMap<String, String>
	 * @exception GeneralException
	 */
	public HashMap<String, String> register(String title, String author, String publisher, String date, String state, String price) throws GeneralException{
		HashMap<String, String> result = new HashMap<String, String>();
		
		try {
			if(title.equals("")) {
				result.put("message", "제목을 입력하셔야합니다.");
			}else {
				ISBNUtil isbnUtil = new ISBNUtil();
				
				Book book = new Book(isbnUtil.create(), title, author, publisher, date, state, price, MarketPlace.my.getId());
				
				myBooks.add(book);
				bookList.add(book);
				
				fp.writeBookFile(bookList, "bookInfo");
				
				result.put("message", "success");
			}
			
			return result;
		}catch (Exception e) {
			new GeneralException("General controller register() method exception.");
			
			return null;
		}
		
	}
	
	/**
	 * Method to return the selected Book.
	 * 
	 * @param index - bookList index.
	 * @return Book
	 * @exception GeneralException
	 */
	public Book select(int index) throws GeneralException {
		try {
			return myBooks.get(index);
		}catch (Exception e) {
			new GeneralException("General controller select() method exception.");
			
			return null;
		}
	}
	
	/**
	 * Method to modify book information.
	 * 
	 * @param index - bookList index.
	 * @param title - input book title information.
	 * @param author - input book author information.
	 * @param publisher - input book publisher information.
	 * @param date - input book date information.
	 * @param state - input book state information.
	 * @param price - input book price information.
	 * @return HashMap<String, String>
	 * @exception GeneralException
	 */
	public HashMap<String, String> modify(int index, String title, String author, String publisher, String date, String state, String price) throws GeneralException {
		HashMap<String, String> result = new HashMap<String, String>();
		
		try {
			if(title.equals("")) {
				result.put("message", "제목을 입력하셔야합니다.");
			}else {
				myBooks.get(index).setTitle(title);
				myBooks.get(index).setAuthor(author);
				myBooks.get(index).setPublisher(publisher);
				myBooks.get(index).setDate(date);
				myBooks.get(index).setState(state);
				myBooks.get(index).setPrice(price);
				
				for(int i=0; i<bookList.size(); i++) {
					Book book = bookList.get(i);
					
					if(myBooks.get(index).getIsbn().equals(book.getIsbn())) {
						bookList.get(i).setTitle(title);
						bookList.get(i).setAuthor(author);
						bookList.get(i).setPublisher(publisher);
						bookList.get(i).setDate(date);
						bookList.get(i).setState(state);
						bookList.get(i).setPrice(price);
						
						break;
					}
				}
				
				fp.writeBookFile(bookList, "bookInfo");
				
				result.put("message", "success");
			}
			
			return result;
		}catch (Exception e) {
			new GeneralException("General controller modify() method exception.");
			
			return null;
		}
		
	}
	
	/**
	 * Method to delete selected book.
	 * 
	 * @param index - bookList index.
	 * @exception GeneralException
	 */
	public void delete(int index) throws GeneralException{
		try {
			Book deletedBook = myBooks.get(index);
			
			for(int i=0; i< bookList.size(); i++) {
				Book book = bookList.get(i);
				
				if(deletedBook.getIsbn().equals(book.getIsbn())) {
					bookList.remove(i);
					
					break;
				}
			}
			
			myBooks.remove(index);
			
			fp.writeBookFile(bookList, "bookInfo");
		}catch (Exception e) {
			new GeneralException("General controller delete() method exception.");
		}	
	}
}
